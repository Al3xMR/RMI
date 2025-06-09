# Serialización de objetos y objetos distribuidos en Java

Estudiante: Kevin Alexis Martínez Rueda

### Pruebas realizadas con:

- `openjdk 24.0.1 2025-04-15`
- `javac 24.0.1`
- `Fedora Linux 42 (WSL)`

### Para instalar Java en Fedora:

```bash
sudo dnf install java-latest-openjdk.x86_64 -y
sudo dnf install java-latest-openjdk-devel -y
javac -version
```

Luego añadir estas lineas al archivo `~/.bashrc`. La versión de Java puede variar según la que se tenga instalada.
```bash
export JAVA_HOME=/usr/lib/jvm/java-24-openjdk
export PATH=$JAVA_HOME/bin:$PATH
```

Y finalmente

```bash
source ~/.bashrc
echo $JAVA_HOME
```

### Para compilar:

```bash
javac interfaz/RemoteCode.java servidor/Servidor.java cliente/Cliente.java matriz/Matriz.java
```

### Para ejecutar el servidor:

```bash
java servidor.Servidor
```

### Para ejecutar el cliente:

```bash
java cliente.Cliente
```