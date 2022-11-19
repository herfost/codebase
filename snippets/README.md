### Serialize Object

```java
# outputStream implements OutputStream;
# object implements Serializable;

# FileOutputStream implements OutputStream
ObjectOutputStream objectOutputStream = new ObjectOutputStrem(outputStream);
objectOutputStream.writeObject(object)
```

### byte[] from Object

```java
ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
ObjectOutputStream objectOutputStream = new(byteArrayOutputStream);

objectOutputStream.writeObject(object);
byte[] objectBytes = byteArrayOutputStream.toByteArray();
```