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

Más de 10 hechizos diferentes con efectos variados.Los hechizos se dividen en tres categorías principales:

#### Hechizos Ofensivos

- **Avada Kedavra**: Hechizo de ataque que inflige daño al objetivo. Es un hechizo muy poderoso y mortal.
- **Crucio**: Hechizo que inflige daño y aturde al objetivo. Solo puede ser lanzado por personajes que puedan usar magia oscura.
- **Sectumsempra**: Hechizo de ataque que inflige daño y aplica un estado de sangrado al objetivo.

#### Hechizos Defensivos

- **Protego**: Escudo protector.
- **Finite Incantatem**: Hechizo que anula todos los efectos mágicos en el objetivo, devolviéndolo a su estado normal.

#### Hechizos de Apoyo

- **Vulnera Sanentur**: Hechizo de curación que restaura la salud del objetivo y aplica un efecto de regeneración durante varios turnos.

Y muchos más… cada hechizo abre nuevas posibilidades estratégicas.

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
