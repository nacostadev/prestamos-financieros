# Sistema de Gestión de Préstamos Financieros

Sistema de gestión de préstamos financieros desarrollado como proyecto académico para el curso de **Calidad de Software**.

El sistema permitirá gestionar clientes, préstamos y pagos mediante una aplicación de escritorio desarrollada en **Java**, con conexión a una base de datos **SQL Server**.

---

## 📌 Descripción del proyecto

El proyecto busca desarrollar un sistema de gestión de préstamos financieros aplicando buenas prácticas de desarrollo de software, principios de diseño, patrones de diseño y técnicas orientadas a mejorar la calidad y mantenibilidad del sistema.

La aplicación será desarrollada utilizando **Java Swing mediante NetBeans**, mientras que la información será almacenada y gestionada en **SQL Server**.

La comunicación entre la aplicación y la base de datos se realizará mediante **JDBC**.

---

## 🎯 Objetivo

Desarrollar un sistema de gestión de préstamos financieros que permita administrar de manera organizada la información relacionada con clientes, préstamos y pagos, aplicando principios de calidad de software y buenas prácticas de programación.

---

## 🛠️ Tecnologías utilizadas

| Tecnología | Uso                               |
| ---------- | --------------------------------- |
| Java       | Lenguaje de programación          |
| Java Swing | Desarrollo de la interfaz gráfica |
| NetBeans   | Entorno de desarrollo             |
| JDBC       | Conexión entre Java y SQL Server  |
| SQL Server | Gestión de la base de datos       |
| Git        | Control de versiones              |
| GitHub     | Repositorio y colaboración        |

---

## 🏗️ Arquitectura

El sistema utilizará una **arquitectura por capas**, con el objetivo de separar responsabilidades y facilitar el mantenimiento y evolución del proyecto.

```text
┌─────────────────────────────┐
│       PRESENTACIÓN          │
│      Java Swing / Forms     │
└──────────────┬──────────────┘
               │
               ▼
┌─────────────────────────────┐
│          SERVICE            │
│      Lógica de negocio      │
└──────────────┬──────────────┘
               │
               ▼
┌─────────────────────────────┐
│            DAO              │
│       Acceso a datos        │
└──────────────┬──────────────┘
               │
               ▼
┌─────────────────────────────┐
│          JDBC               │
│     Conexión a BD           │
└──────────────┬──────────────┘
               │
               ▼
┌─────────────────────────────┐
│         SQL SERVER          │
│        Base de datos        │
└─────────────────────────────┘
```

### Principios y buenas prácticas

Durante el desarrollo se buscará aplicar:

* Principios SOLID.
* Separación de responsabilidades.
* Bajo acoplamiento.
* Alta cohesión.
* Reutilización de código.
* Manejo adecuado de excepciones.
* Validación de datos.
* Uso de `PreparedStatement`.
* Convenciones de nomenclatura.
* Control de versiones mediante Git.
* Documentación técnica.
* Pruebas de software.

### Patrones de diseño

Se evaluará la aplicación de patrones adecuados a las necesidades del sistema, entre ellos:

* DAO (Data Access Object).
* Service Layer.
* Factory.
* MVC.

Los patrones serán utilizados cuando aporten una solución real al diseño del sistema, evitando complejidad innecesaria.

---

## 📂 Estructura del repositorio

```text
sistema-prestamos-financieros/
│
├── src/
│   └── Código fuente Java
│
├── database/
│   ├── tables/
│   ├── procedures/
│   ├── views/
│   └── seed/
│
├── docs/
│   ├── arquitectura/
│   ├── diagramas/
│   └── documentación/
│
├── README.md
└── .gitignore
```

La carpeta `database/` contendrá los scripts necesarios para crear y actualizar la base de datos SQL Server.

La base de datos será desarrollada y ejecutada de manera independiente en **SQL Server**. El repositorio almacenará los scripts SQL necesarios para reproducir y versionar la estructura de la base de datos.

---

## 📋 Funcionalidades previstas

### Gestión de clientes

* Registrar clientes.
* Actualizar información de clientes.
* Consultar clientes.
* Buscar clientes.
* Cambiar estado del cliente.

### Gestión de préstamos

* Registrar préstamos.
* Consultar préstamos.
* Calcular cuotas.
* Consultar saldo pendiente.
* Gestionar estados del préstamo.

### Gestión de pagos

* Registrar pagos.
* Consultar pagos.
* Actualizar saldo del préstamo.
* Consultar cuotas pendientes.

### Gestión de usuarios

* Inicio de sesión.
* Gestión de usuarios.
* Control de acceso según el tipo de usuario.

### Reportes

* Consulta de préstamos.
* Consulta de pagos.
* Estado de préstamos.
* Información de clientes.

> Las funcionalidades podrán modificarse o ampliarse durante el desarrollo del proyecto.

---

## 🗄️ Base de datos

La base de datos será desarrollada utilizando **Microsoft SQL Server**.

Los scripts estarán versionados dentro del repositorio para permitir que todos los integrantes trabajen sobre una estructura de base de datos consistente.

```text
database/
│
├── tables/
├── constraints/
├── procedures/
├── views/
└── seed/
```

Cada cambio importante realizado en la estructura de la base de datos deberá quedar registrado mediante un script versionado.

---

## 🔌 Conexión con la base de datos

La aplicación Java se conectará a SQL Server mediante **JDBC**.

La configuración de conexión no deberá contener credenciales reales dentro del código fuente ni dentro del repositorio.

Se utilizará un archivo de configuración de ejemplo para que cada integrante pueda configurar su entorno local.

```text
database.properties.example
```

---

## 🌿 Control de versiones

El proyecto utilizará **Git y GitHub** para el control de versiones y trabajo colaborativo.

### Ramas principales

```text
main
└── develop
```

Las nuevas funcionalidades se desarrollarán mediante ramas independientes:

```text
feature/clientes
feature/prestamos
feature/pagos
feature/usuarios
feature/reportes
```

Los cambios deberán integrarse a `develop` mediante Pull Requests.

La rama `main` contendrá versiones estables del sistema.

---

## 📝 Convención de commits

Se utilizará una convención basada en **Conventional Commits**.

Ejemplos:

```text
feat: implementar registro de clientes
feat: agregar módulo de préstamos
fix: corregir cálculo de cuota
refactor: separar lógica de negocio del formulario
test: agregar pruebas de PrestamoService
docs: actualizar documentación
chore: configurar proyecto
```

---

## 👥 Equipo de desarrollo

| Integrante          | Rol        |
| --------------------| ---------- |
| Naylin Acosta       | Desarrollo |
| Sthefany Chuquipoma | Desarrollo |
| Elsa Mantilla       | Desarrollo |
| Ernesto Robles      | Desarrollo |
| Jhonatan Levi       | Desarrollo |

---

## 🚀 Instalación

### Requisitos

* JDK
* NetBeans
* SQL Server
* SQL Server Management Studio
* Git

### Configuración

1. Clonar el repositorio.

```bash
git clone <URL_DEL_REPOSITORIO>
```

2. Configurar la base de datos en SQL Server.

3. Ejecutar los scripts ubicados en la carpeta `database/`.

4. Configurar los datos de conexión JDBC.

5. Abrir el proyecto en NetBeans.

6. Ejecutar la aplicación.

> Las instrucciones de instalación serán ampliadas conforme avance el desarrollo.

---

## 📚 Documentación

La documentación técnica del proyecto se encontrará en la carpeta:

```text
/docs
```

Aquí se incluirán:

* Arquitectura del sistema.
* Diagramas.
* Modelo de base de datos.
* Reglas de negocio.
* Casos de uso.
* Manual de instalación.
* Manual de usuario.
* Evidencias de pruebas.

---

## 📌 Estado del proyecto

**En desarrollo 🚧**

Este proyecto se encuentra en etapa inicial de planificación y desarrollo.


⚠️ Importante:
- El archivo ConexionBD.java está ignorado en Git.
- Usa el archivo de ejemplo en src/main/java/com/prestamos/config/conexionbd/Example.java
- Copia ese archivo y renómbralo a ConexionBD.java en tu máquina.
- Edita los parámetros con tu configuración local (servidor, puerto, etc).
