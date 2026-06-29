# ⚔️ Trabajo Práctico 2: Magos Contra Mortífagos

## Descripción del Proyecto

Este proyecto es un simulador de **batallas magicas** entre dos facciones de magos: los **Magos** y los **Mortífagos**. Implementado en **Java**, el sistema simula batallas completas con un sistema de hechizos, puntos de vida, maná y estados especiales.

Es un trabajo práctico para la asignatura **Paradigmas de Programación**, demostrando conceptos como:

- **Programación Orientada a Objetos (POO)**
- **Patrones de Diseño** (State, Factory, Strategy)
- **Polimorfismo y Herencia**
- **Gestión de Estados**
- **Testing Unitario**

---

## 🎮 Características Principales

### Sistema de Personajes

- **Magos**: Personajes defensivos y de apoyo
- **Mortífagos**: Personajes ofensivos y agresivos
- **Variantes**: Aurors, Profesores, Estudiantes, Comandantes, Seguidores

### Sistema de Combate

- Batallones de personajes que se enfrentan en rondas
- Turnos alternados entre facciones
- Sistema de ataque y defensa inteligente
- Cálculo dinámico de daño basado en nivel de magia

### Sistema de Hechizos

Más de 10 hechizos diferentes con efectos variados:

#### Hechizos Ofensivos

- **Avada Kedavra**: Hechizo letal instantáneo
- **Crucio**: Hechizo de tortura con daño continuo
- **Sectumsempra**: Cortes mágicos
- **Desmaius**: Desmayar al oponente

#### Hechizos Defensivos

- **Protego**: Escudo protector
- **Expecto Patronum**: Invocación defensiva
- **Finite Incantatem**: Contramagia

#### Hechizos de Apoyo

- **Vulnera Sanentur**: Curación mágica
- **Sobrecarga Mágica**: Amplificación de poder

### Sistema de Estados

Los personajes pueden experimentar diferentes estados que afectan su capacidad de combate:

- **Normal**: Estado base
- **Aturdido**: No puede atacar
- **Evasión**: Esquiva ataques
- **Escudo**: Protección activa
- **Sangrado**: Pierde vida cada ronda
- **Regeneración**: Recupera vida
- **Furia**: Ataca con poder aumentado

---

## 📁 Estructura del Proyecto

```
TrabajoPractico2-MagosContraMortifagos/
│
├── README.md                          # Este archivo
├── Proyecto/                          # Carpeta principal del proyecto
│   ├── pom.xml                        # Configuración Maven
│   ├── src/
│   │   ├── main/
│   │   │   └── java/
│   │   │       └── com/facultad/tp/
│   │   │           ├── Personaje.java            # Clase base de personajes
│   │   │           ├── Mago.java                 # Personaje Mago
│   │   │           ├── Mortifago.java            # Personaje Mortífago
│   │   │           ├── Batallon.java             # Gestión de batallones
│   │   │           ├── BatallaMagosVsMortifagos  # Simulador principal
│   │   │           ├── Hechizo.java              # Interface de hechizos
│   │   │           ├── FabricaHechizos.java      # Factory pattern
│   │   │           ├── hechizos/                 # Implementaciones de hechizos
│   │   │           │   ├── AvadaKedavra.java
│   │   │           │   ├── Protego.java
│   │   │           │   └── ... (12+ hechizos)
│   │   │           └── estados/                  # Sistema de estados
│   │   │               ├── EstadoPersonaje.java  # Interface base
│   │   │               ├── EstadoNormal.java
│   │   │               ├── EstadoAturdido.java
│   │   │               └── ... (7 estados)
│   │   └── test/
│   │       └── java/                 # Tests unitarios
│   └── target/                        # Compilación Maven
```

---

## 🏗️ Patrones de Diseño Utilizados

### 1. **State**

Gestión de estados mediante la clase `EstadoPersonaje` y sus implementaciones.

### 2. **Factory**

`FabricaHechizos` para crear instancias de hechizos de forma centralizadarilada.

### 3. **Strategy**

Diferentes estrategias de ataque según el tipo de personaje.

### 4. **Template Method**

Estructura base en `Personaje` que es extendida por `Mago` y `Mortifago`.

---

## 🎯 Objetivos Educativos

Este proyecto demuestra:
✅ Implementación de sistemas complejos orientados a objetos
✅ Uso de patrones de diseño en contexto real
✅ Gestión de estado y transiciones
✅ Testing y validación de código
✅ Simulación de sistemas de juego/batalla
✅ Arquitectura escalable y mantenible

---
