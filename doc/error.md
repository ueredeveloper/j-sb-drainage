14/04/2023
Ao inserir documento com fk ou processo com fk, no momento de buscá-los dá erro. 

```
	at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:722) ~[jackson-databind-2.10.1.jar:2.10.1]
	at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:166) ~[jackson-databind-2.10.1.jar:2.10.1]
	at com.fasterxml.jackson.databind.ser.std.CollectionSerializer.serializeContents(CollectionSerializer.java:145) ~[jackson-databind-2.10.1.jar:2.10.1]
	at com.fasterxml.jackson.databind.ser.std.CollectionSerializer.serialize(CollectionSerializer.java:107) ~[jackson-databind-2.10.1.jar:2.10.1]
	at com.fasterxml.jackson.databind.ser.std.CollectionSerializer.serialize(CollectionSerializer.java:25) ~[jackson-databind-2.10.1.jar:2.10.1]
	at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:727) ~[jackson-databind-2.10.1.jar:2.10.1]
	at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:722) ~[jackson-databind-2.10.1.jar:2.10.1]
	at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:166) ~[jackson-databind-2.10.1.jar:2.10.1]
```
	