### Serialize Object

```java
# outputStream implements OutputStream;
# object implements Serializable;

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

### UDP

```java
public class Network {

    private DatagramSocket socket;

    public Network(DatagramSocket socket) {
        this.socket = socket;
    }

    public Network(int port) throws SocketException {
        this.socket = new DatagramSocket(port);
    }

    public DatagramSocket getSocket() {
        return socket;
    }

    public void send(DatagramPacket packet) throws IOException {
        socket.send(packet);
    }

    public void send(DatagramPacket packet, boolean broadcast) throws IOException {
        socket.setBroadcast(broadcast);
        send(packet);
        socket.setBroadcast(false);
    }

    public void send(byte[] message, InetAddress address, int port) throws IOException {
        send(new DatagramPacket(message, message.length, address, port));
    }

    public void send(byte[] message, InetAddress address, int port, boolean broadcast) throws IOException {
        send(new DatagramPacket(message, message.length, address, port), broadcast);
    }

    public DatagramPacket receive(int length, int timeout) throws SocketTimeoutException, IOException {
        DatagramPacket packet = new DatagramPacket(new byte[length], length);
        socket.setSoTimeout(timeout);
        socket.receive(packet);
        socket.setSoTimeout(0);

        return packet;
    }

    public DatagramPacket receive() throws SocketTimeoutException, IOException {
        return receive(Configuration.PACKET_LENGTH, Configuration.TIMEOUT);
    }
}
```

### TCP

```java
# Client
public class ClientNetwork {
    private Socket socket;
    private InputStream in;
    private OutputStream out;

    public ClientNetwork(Socket socket) throws IOException {
        this.socket = socket;
        in = socket.getInputStream();
        out = socket.getOutputStream();
    }

    public ClientNetwork(InetAddress address, int port) throws IOException {
        this(new Socket(address, port));
    }
}

# Server
public class ServerNetwork {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private InputStream in;
    private OutputStream out;

    public ServerNetwork(ServerSocket serverSocket) throws IOException {
        this.serverSocket = serverSocket;
        socket = serverSocket.accept();
        in = clientSocket.getInputStream();
        out = clientSocket.getOutputStream();
    }

    public ServerNetwork(int port) throws IOException {
        this(new ServerSocket(port));
    }
}
```

### ByteBuffer

```java

```

### Persistence

```java
# Interfaccia
public interface IPersistence<K, T extends Serializable & Keyable<K> & MyCloneable<T>> {
    public void create(T value) throws IllegalArgumentException;
    public T read(K key) throws IllegalArgumentException;
    public void update(T value) throws IllegalArgumentException;
    public void delete(K key) throws IllegalArgumentException;
    public List<T> getAll() throws IllegalArgumentException;
}

# Layer di persistenza concreto
public class Persistence<K, T extends Serializable & Keyable<K> & MyCloneable<T>> implements IPersistence<K, T> {

    private final ArrayList<T> items;

    public Persistence() {
        items = new ArrayList<>();
    }

    @Override
    public synchronized void create(T value) throws IllegalArgumentException {
        try {
            T item = read(value.getKey());
            throw new IllegalArgumentException("Key already been used: @key = " + item.getKey());
        } catch (IllegalArgumentException ex) {
            items.add(value.getClone());
        }
    }

    @Override
    public synchronized  T read(K key) throws IllegalArgumentException {
        T item = getItem(key);
        if (item == null) throw new IllegalArgumentException("Invalid Key");

        return item.getClone();
    }

    @Override
    public synchronized void update(T value) throws IllegalArgumentException {
        T item = getItem(value.getKey());
        if (item == null) throw new IllegalArgumentException("Invalid Key");

        item = value.getClone();
    }

    @Override
    public synchronized void delete(K key) throws IllegalArgumentException {
        T item = getItem(key);
        if (item == null) throw new IllegalArgumentException("Invalid Key");

        items.remove(getItem(item.getKey()));
    }

    @Override
    public synchronized List<T> getAll() throws IllegalArgumentException {
        if (!items.isEmpty()) {
            List<T> copy = new ArrayList<>();
            for (T item : items) copy.add(item.getClone());
            return copy;
        }

        throw new IllegalArgumentException("Empty Persistence");
    }

    private synchronized T getItem(K key) {
        for (T item : items) {
            if (item.getKey().equals(key)) return item;
        }
        return null;
    }

    @Override
    synchronized public String toString() {
        StringBuilder builder = new StringBuilder();
        items.forEach(item -> {
            builder.append(item.toString()).append('\n');
        });

        return builder.toString();
    }
}

# Persistenza concreta
public class ProdottoPersistence {

    private static ProdottoPersistence instance;
    private Persistence<String, Product<String>> persistence

    private ProdottoPersistence() {}

    public static synchronized ProdottoPersistence() getInstance() {
        if (instance == null) instance = new ProdottoPersistence();
        return instance;
    }

    public Persistence<String, Product<String>> getPersistence() {
        return persistence;
    }
}
```

### Caratteristiche Oggetto Persistenza

```java
# Ha una chiave di accesso
public interface Keyable<K> {
    public K getKey();
}

# Restituisce suoi cloni
public interface MyCloneable<T> extends Cloneable {
    public T getClone();
}

# Classe concreta (Java Bean)
public class Product<K> implements Serializable, Keyable<K>, MyCloneable<Product<K>> {

    private K key;
    private String description;
    private int quantity;

    public Product(K key, String description, int quantity) {
        this.key = key;
        this.description = description;
        this.quantity = quantity;
    }

    public Product() {
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public Product getClone() {
        try {
            return (Product) super.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product<?> other = (Product<?>) obj;
        if (this.quantity != other.quantity) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.key, other.key)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Product { " + "key = " + key + ", description = " + description + ", quantity = " + quantity + " }";
    }
}
```
