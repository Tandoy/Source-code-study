/**
 * 第二步：序列化key-value
 */
byte[] serializedKey;
try {
    //选择key序列化类
    serializedKey = keySerializer.serialize(record.topic(), record.key());
} catch (ClassCastException cce) {
    throw new SerializationException("Can't convert key of class " + record.key().getClass().getName() +
        " to class " + producerConfig.getClass(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG).getName() +
        " specified in key.serializer");
}
byte[] serializedValue;
try {
    //选择value序列化类
    serializedValue = valueSerializer.serialize(record.topic(), record.value());
} catch (ClassCastException cce) {
    throw new SerializationException("Can't convert value of class " + record.value().getClass().getName() +
        " to class " + producerConfig.getClass(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG).getName() +
        " specified in value.serializer");
}