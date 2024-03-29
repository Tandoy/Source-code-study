/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.runtime.io.network.partition;

import org.apache.flink.core.memory.MemorySegment;
import org.apache.flink.runtime.io.network.buffer.Buffer;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Data of different channels can be appended to a {@link SortBuffer} and after the {@link
 * SortBuffer} is finished, the appended data can be copied from it in channel index order.
 */

/**
 * 基于 hash 的实现会产生大量的文件，而减少文件的数量有利于提高稳定性和性能。
 * Sort-Spill-Merge 的方式被分布式计算系统广泛采纳以达到这一目标，首先将数据写入内存缓冲区，
 * 当内存缓冲区填满后对数据进行排序(桶排序)，排序后的数据被写出到一个文件中，这样总的文件数量是：（总数据量 / 内存缓冲区大小），从而文件数量被减少。
 * 当所有数据写出完成后，将产生的文件合并成一个文件，从而进一步减少文件数量并增大每个数据分区的大小（有利于顺序读取）。
 *
 * https://mp.weixin.qq.com/s/M5lGOYu0Bwaspa8G0x5NHQ
 */
public interface SortBuffer {

    /**
     * Appends data of the specified channel to this {@link SortBuffer} and returns true if all
     * bytes of the source buffer is copied to this {@link SortBuffer} successfully, otherwise if
     * returns false, nothing will be copied.
     */
    boolean append(ByteBuffer source, int targetChannel, Buffer.DataType dataType)
            throws IOException;

    /**
     * Copies data in this {@link SortBuffer} to the target {@link MemorySegment} in channel index
     * order and returns {@link BufferWithChannel} which contains the copied data and the
     * corresponding channel index.
     */
    BufferWithChannel copyIntoSegment(MemorySegment target);

    /** Returns the number of records written to this {@link SortBuffer}. */
    long numRecords();

    /** Returns the number of bytes written to this {@link SortBuffer}. */
    long numBytes();

    /** Returns true if there is still data can be consumed in this {@link SortBuffer}. */
    boolean hasRemaining();

    /** Finishes this {@link SortBuffer} which means no record can be appended any more. */
    void finish();

    /** Whether this {@link SortBuffer} is finished or not. */
    boolean isFinished();

    /** Releases this {@link SortBuffer} which releases all resources. */
    void release();

    /** Whether this {@link SortBuffer} is released or not. */
    boolean isReleased();
}
