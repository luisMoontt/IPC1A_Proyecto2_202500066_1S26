# 🎮 GameZone Pro

> Sistema de gestión de videojuegos desarrollado en Java con Swing  
> **IPC1 — Proyecto 2 | 1S26 | Carné: 202500066**

---

## 📋 Requisitos previos

Antes de ejecutar el proyecto asegúrate de tener instalado:

| Herramienta | Versión mínima | Verificar con |
|-------------|---------------|---------------|
| Java JDK | 11 o superior | `java -version` |
| Apache Maven | 3.6 o superior | `mvn -version` |
| NetBeans IDE | 19+ (opcional) | — |
| Git | Cualquiera | `git --version` |

---

## 🚀 Pasos de ejecución

### Opción A — Clonar el repositorio

```bash
# 1. Clonar
git clone https://github.com/tu-usuario/GameZone_Pro.git

# 2. Entrar a la carpeta del proyecto
cd GameZone_Pro
```

### Opción B — Descargar el ZIP

1. Descarga el archivo `GameZone_Pro.zip` desde el repositorio
2. Extrae el contenido en la ubicación que prefieras
3. Abre una terminal y navega hasta la carpeta extraída:

```bash
cd ruta/a/GameZone_Pro
```

---

### ▶️ Ejecutar con Maven (terminal)

```bash
# 1. Compilar el proyecto y descargar dependencias
mvn clean install

# 2. Ejecutar
mvn exec:java -Dexec.mainClass="com.ipc1.proyecto2.main.GameZone_Pro"
```

### ▶️ Ejecutar con NetBeans

1. Abre NetBeans
2. **File → Open Project** → selecciona la carpeta `GameZone_Pro`
3. Clic derecho sobre el proyecto → **Clean and Build**
4. Presiona **Run Project** (F6)

### ▶️ Ejecutar el JAR directamente

```bash
# Primero genera el JAR
mvn clean package

# Luego ejecútalo
java -jar target/GameZone_Pro-1.0-SNAPSHOT.jar
```

> ⚠️ Asegúrate de correr el JAR desde la misma carpeta donde está la carpeta `docs/`, ya que ahí se almacenan todos los datos.

---

## 📁 Estructura del proyecto

```
GameZone_Pro/
├── docs/                        # Datos persistentes (se crean automáticamente)
│   ├── users.txt                # Usuarios registrados
│   ├── catalogo.txt             # Catálogo de videojuegos
│   ├── logros.txt               # Logros por usuario
│   ├── historial.txt            # Historial de compras
│   ├── torneos.txt              # Torneos disponibles
│   ├── tickets_vendidos.txt     # Tickets comprados
│   └── images/                  # Imágenes del sistema
├── src/
│   └── main/java/com/ipc1/proyecto2/
│       ├── main/                # Punto de entrada (GameZone_Pro.java)
│       ├── models/              # Entidades: User, VideoGames, Achievements...
│       ├── controlllers/        # Lógica de negocio y persistencia
│       └── views/               # Interfaces Swing: Login, Module, paneles...
├── pom.xml                      # Configuración Maven y dependencias
└── README.md
```

---

## ⚙️ Funcionamiento del programa

### 1. 🔐 Login / Registro

Al iniciar el programa se muestra la pantalla de **Login**.

- Ingresa tu usuario y contraseña para acceder
- Si no tienes cuenta, usa el botón **Registrarse** para crear una nueva
- Los usuarios se guardan automáticamente en `docs/users.txt`

---

### 2. 🏠 Módulo principal

Tras iniciar sesión accedes al módulo principal con un menú de navegación en la parte superior:

| Sección | Descripción |
|---------|-------------|
| **Tienda de Juegos** | Explora el catálogo y compra videojuegos |
| **Eventos** | Consulta y compra tickets para torneos |
| **Mi Álbum** | Visualiza tu colección de cartas |
| **Ranking** | Tabla de posiciones de todos los usuarios |
| **Usuario (esquina)** | Ver perfil, logros y cerrar sesión |

---

### 3. 🛒 Tienda de Videojuegos

- El catálogo se muestra en un **grid de tarjetas**
- Haz **clic en una tarjeta** para ver los detalles del juego
- Selecciona la cantidad con el spinner y presiona **Agregar al carrito**
- El carrito aparece en el panel derecho en tiempo real
- Puedes **aumentar, disminuir o eliminar** ítems del carrito
- Presiona **Comprar** para finalizar — el sistema valida el stock de todos los ítems (todo o nada)

---

### 4. 👤 Perfil de usuario

- Muestra el **nombre**, **nivel** y **barra de XP**
- Lista todos los **logros** del usuario:
  - 🏆 **Dorado** — logro desbloqueado
  - 🔒 **Gris** — logro pendiente
- Al ganar XP subes de nivel automáticamente:

| Nivel | Título | XP necesario |
|-------|--------|-------------|
| 1 | Aprendiz | 0 – 499 |
| 2 | Jugador | 500 – 1,499 |
| 3 | Veterano | 1,500 – 3,499 |
| 4 | Maestro | 3,500 – 6,999 |
| 5 | Leyenda | 7,000+ |

---

### 5. 🏆 Eventos y Torneos

- Consulta los torneos disponibles
- Compra tickets para participar
- Los tickets se registran en `docs/tickets_vendidos.txt`

---

### 6. 🚪 Cerrar sesión

Presiona el botón **CERRAR SESIÓN** en la esquina superior derecha para volver a la pantalla de Login sin cerrar la aplicación.

---

## 🗄️ Persistencia de datos

Todos los datos se almacenan en archivos `.txt` dentro de la carpeta `docs/` usando `|` como separador:

```
# Ejemplo users.txt
noely|123456|250|2|

# Ejemplo catalogo.txt
VDG-001|The Legend of Zelda|Aventura|299.99|Nintendo Switch|15|Explora Hyrule...|

# Ejemplo logros.txt
1|noely|Primer Combate|Gana tu primera batalla|100|true|
```

> La carpeta `docs/` se crea automáticamente si no existe al iniciar el programa.

---

## 📦 Dependencias

```xml
<!-- FlatLaf — Tema oscuro moderno -->
<dependency>
    <groupId>com.formdev</groupId>
    <artifactId>flatlaf</artifactId>
    <version>3.4.1</version>
</dependency>

<!-- AbsoluteLayout — GUI Builder de NetBeans -->
<dependency>
    <groupId>org.netbeans.external</groupId>
    <artifactId>AbsoluteLayout</artifactId>
    <version>RELEASE290</version>
</dependency>
```

---

## 👨‍💻 Autor

**Luis Monterroso**  
Carné: 202500066  
Universidad de San Carlos de Guatemala  
Introducción a la Programación y Computación 1 — 1S26
