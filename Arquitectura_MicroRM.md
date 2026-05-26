# Documento de Arquitectura de Software (DAS)

## Proyecto

MicroRM

## Arquitectos

Dany Alexander Cardona Gómez

---

## Control de cambios y revisiones

| Versión | Fecha | Tipo | Descripción | Autor |
| --- | --- | --- | --- | --- |
| 1 | 19/05/2026 | Creación | Versión inicial del documento | Dany Alexander Cardona Gómez |

---

## Contenido

1. [Propósito del proyecto](#propósito-del-proyecto)
2. [Motivadores de la arquitectura](#motivadores-de-la-arquitectura)
   - 2.1 [Restricciones técnicas](#restricciones-técnicas)
   - 2.2 [Restricciones de negocio](#restricciones-de-negocio)
   - 2.3 [Atributos de calidad](#atributos-de-calidad)
   - 2.4 [Funcionalidades críticas](#funcionalidades-críticas)
3. [Tácticas y estrategias](#tácticas-y-estrategias)
4. [Modelo de contexto](#modelo-de-contexto)
   - 4.1 [Componentes](#componentes)
   - 4.2 [Modelo de contexto esquema](#modelo-de-contexto-esquema)
5. [Arquetipo de solución/referencia](#arquetipo-de-soluciónreferencia)
   - 5.1 [Componentes](#componentes-1)
   - 5.3 [Arquetipo de solución/referencia diagrama](#arquetipo-de-soluciónreferencia-diagrama)
6. [Arquitectura de solución/referencia](#arquitectura-de-soluciónreferencia)
   - 6.1 [Arquitectura de solución/referencia diagrama](#arquitectura-de-soluciónreferencia-diagrama)
7. [Línea base arquitectónica](#línea-base-arquitectónica)
   - 7.1 [Línea base arquitectónica de componentes](#línea-base-arquitectónica-de-componentes)
   - 7.2 [Estilos y patrones arquitectónicos adoptados](#estilos-y-patrones-arquitectónicos-adoptados)
8. [Justificación alternativa de solución](#justificación-alternativa-de-solución)
   - 8.1 [Ventajas](#ventajas)
   - 8.2 [Desventajas](#desventajas)
9. [Vistas de arquitectura del sistema](#vistas-de-arquitectura-del-sistema)

---

## Propósito del proyecto

MicroRM es un sistema de gestión de laboratorio de microbiología clínica diseñado para un laboratorio de nivel hospitalario. Su objetivo es automatizar y trazabilizar completamente el proceso microbiológico desde la recepción de la muestra hasta la entrega del resultado final, cumpliendo con los estándares de calidad ISO 15189 y las normativas de protección de datos colombianas (Ley 1581 de 2012).

---

## Motivadores de la arquitectura

Los motivadores de la arquitectura son los factores o las necesidades funcionales de calidad, técnicas y de negocio que orientan, condicionan y justifican las decisiones de diseño de un sistema.

Estos motivadores definen el rumbo del proyecto al delimitar su alcance, establecer las prioridades e identificar las restricciones que resultan no negociables para alcanzar la solución.

### Restricciones técnicas

Las restricciones técnicas son condiciones del entorno o del contexto del proyecto que el equipo de desarrollo no puede cambiar y que, por tanto, afectan directamente las decisiones de diseño.

- **Desarrollo on-premise:** El sistema debe instalarse en un servidor Windows Server en las instalaciones del hospital, sin depender de infraestructura en la nube.
- **Recursos limitados:** El servidor tiene limitaciones de RAM y CPU que condicionan la selección de tecnologías y la escalabilidad del sistema.
- **Infraestructura existente:** El sistema debe integrarse con el Active Directory hospitalario, el HIS/LIS existente y la red interna del hospital.
- **Desarrollo individual:** Un solo desarrollador construye el sistema, lo que impacta la complejidad de las soluciones adoptadas.

### Restricciones de negocio

Las restricciones de negocio son condiciones externas al equipo de desarrollo, impuestas por el contexto organizacional, normativo o regulatorio del proyecto.

| # | Restricción | Descripción | Justificación |
| --- | --- | --- | --- |
| RN-01 | Cumplimiento de la Ley 1581 de 2012 | El sistema maneja datos sensibles de pacientes, por lo que debe garantizar su confidencialidad, uso autorizado y posibilidad de corrección según lo exige la ley colombiana de habeas data. | Al tratar información clínica identificable de personas naturales, el sistema está sujeto a esta ley de forma obligatoria. |
| RN-02 | Cumplimiento de la norma ISO 15189 | Esta norma internacional define los requisitos de calidad y competencia para laboratorios clínicos, incluyendo la trazabilidad de muestras, la validación de resultados y el control de registros. | MicroRM está diseñado para operar en un laboratorio de microbiología clínica. |
| RN-03 | Contexto de uso hospitalario con infraestructura preexistente | El sistema debe integrarse con la infraestructura tecnológica ya instalada en el hospital. | No es viable reemplazar sistemas institucionales que ya funcionan. |
| RN-04 | Proyecto de carácter académico con alcance delimitado | MicroRM es un proyecto desarrollado en el marco de un curso de arquitectura de software. | El contexto académico impone limitaciones reales de tiempo, recursos y complejidad. |
| RN-05 | Sistema diseñado para un único laboratorio | El sistema está pensado para operar en un solo laboratorio clínico. | El caso de uso del proyecto es específico. |
| RN-06 | Operación restringida a usuarios internos del laboratorio | El sistema no está diseñado para acceso externo. | El flujo de trabajo del laboratorio es interno. |

### Atributos de calidad

Los atributos de calidad son características no funcionales que describen cómo debe comportarse el sistema, más allá de lo que debe hacer. Estos ayudan a priorizar esfuerzos durante el desarrollo.

#### Atributo de calidad 1: Usabilidad

La usabilidad es el atributo que determina qué tan fácil y eficiente resulta para los usuarios interactuar con el sistema. En MicroRM, este atributo es especialmente importante porque el personal del laboratorio no necesariamente tiene formación técnica avanzada.

**Escenarios de calidad:**
- ESC-CAL-USA-0006: Confirmación de registro de muestra exitoso con resumen visible
- ESC-CAL-USA-0010: Alerta de contaminación por tres o más morfotipos en urocultivo
- ESC-CAL-USA-0021: Actualización exitosa del estado de muestra de recibida a en análisis

#### Atributo de calidad 2: Seguridad

La seguridad es el atributo que garantiza que el sistema proteja la información, controle el acceso de los usuarios y registre las acciones realizadas.

**Escenarios de calidad:**
- ESC-CAL-SEG-0011: Cierre automático de sesión tras 10 minutos de inactividad

#### Atributo de calidad 3: Fiabilidad

La fiabilidad es el atributo que garantiza que el sistema operate correctamente, que los datos sean íntegros y consistentes.

**Escenarios de calidad:**
- ESC-CAL-FIA-0001: Registro exitoso de muestra con código único generado automáticamente
- ESC-CAL-FIA-0005: Bloqueo de modificación de muestra cerrada con mensaje informativo
- ESC-CAL-FIA-0007: Confirmación requerida antes de guardar cambios en coloración de Gram

#### Atributo de calidad 4: Disponibilidad

La disponibilidad garantiza que el sistema permanezca operativo y que sea capaz de recuperarse ante fallos sin pérdida de información.

**Escenarios de calidad:**
- ESC-CAL-DIS-0006: Preservación de información de seguimiento de incubación ante cierre inesperado

#### Atributo de calidad 5: Capacidad para ser mantenido

La mantenibilidad determina qué tan fácil resulta modificar, corregir o extender el sistema a lo largo del tiempo.

**Escenarios de calidad:**
- ESC-CAL-CSM-0019: Consulta exitosa del historial completo de actividad de una muestra desde recepción hasta cierre
- ESC-CAL-CSM-0021: Registro exitoso del tipo de agar y fecha de siembra para cada cultivo de muestra

### Funcionalidades críticas

Las funcionalidades críticas son aquellas capacidades del sistema sin las cuales el sistema no puede cumplir su propósito principal.

- **FC-001:** Registro de muestras biológicas
- **FC-002:** Gestión del ciclo de vida de la muestra
- **FC-003:** Registro de siembra en medios de cultivo
- **FC-004:** Seguimiento diario de incubación
- **FC-005:** Detección automática de contaminación en urocultivos
- **FC-006:** Registro y consulta de resultados microbiológicos
- **FC-007:** Trazabilidad e historial de auditoría
- **FC-008:** Autenticación y control de acceso por roles
- **FC-009:** Generación de reportes del laboratorio
- **FC-010:** Integración con el HIS/LIS hospitalario

---

## Tácticas y estrategias

Una táctica es una decisión de diseño concreta que el arquitecto toma para satisfacer un atributo de calidad específico. Una estrategia es un conjunto de tácticas relacionadas que trabajan juntas.

### Táctica 1: Máquina de estados finitos para el ciclo de vida de muestras

Implementar una máquina de estados finitos (FSM) en la capa de negocio que controle las transiciones válidas entre los estados de una muestra: Recibida → En análisis → Finalizada.

**Satisface:** Escenarios ESC-CAL-FIA-0005 y ESC-CAL-USA-0021, FC-002

### Táctica 2: Motor de reglas de dominio en la capa de negocio

Implementar un motor de reglas que centralice y evalúe las reglas del dominio microbiológico, como la detección de contaminación en urocultivos con tres o más morfotipos.

**Satisface:** Escenarios ESC-CAL-USA-0006, ESC-CAL-USA-0010, FC-001, FC-003, FC-005

### Táctica 3: Event Sourcing con MongoDB append-only para auditoría

Registrar cada cambio relevante sobre una muestra como un evento inmutable en una colección de MongoDB configurada en modo solo escritura.

**Satisface:** Escenarios ESC-CAL-CSM-0019 y ESC-CAL-CSM-0021, FC-007

### Táctica 4: Separación de operaciones de consulta y modificación de datos

Separar los flujos de lectura y escritura del sistema en dos caminos independientes.

**Satisface:** Escenario ESC-CAL-REN-0005, FC-007, FC-009

### Táctica 5: Autoguardado periódico durante el seguimiento de incubación

Implementar un mecanismo de autoguardado automático cada 60 segundos, sin requerir acción del usuario.

**Satisface:** Escenario ESC-CAL-DIS-0006, FC-004

### Táctica 6: Cierre automático de sesión por inactividad

Implementar un hilo programado que monitoree el tiempo de inactividad de cada sesión activa y ejecute el cierre automático al alcanzar los 10 minutos.

**Satisface:** Escenario ESC-CAL-SEG-0011, FC-008

### Táctica 7: Control de acceso basado en roles

Definir e implementar un modelo de control de acceso que asigne permisos diferenciados a cada perfil de usuario.

**Satisface:** Escenarios ESC-CAL-SEG-0011 y ESC-CAL-FIA-0005

### Táctica 8: Arquitectura en capas con separación estricta de responsabilidades

Organizar el backend en cuatro capas bien definidas (Presentación, Integración, Negocio y Datos).

**Satisface:** Todos los atributos de calidad

### Táctica 9: Integración con HIS/LIS mediante HL7 FHIR

Implementar la capa de integración utilizando HAPI FHIR como librería de interoperabilidad.

**Satisface:** FC-010, restricción RN-03

### Táctica 10: Gestión de parámetros del dominio mediante Strapi

Gestionar los catálogos de configuración del dominio a través de Strapi como sistema de gestión de contenido headless.

**Satisface:** Escenario ESC-CAL-CSM-0021, FC-003, FC-005

---

## Modelo de contexto

Un modelo de contexto es una representación que muestra el sistema como una caja central y define todo lo que existe a su alrededor: quiénes lo usan, con qué sistemas externos se comunica y qué información intercambia.

### Componentes

**MicroRM:** El sistema a construir. Todo lo que lo rodea son actores o sistemas externos con los que interactúa.

**Usuario final:** El personal del laboratorio que usa el sistema desde un computador de escritorio o portátil.

**Internet / Intranet hospitalaria:** La red por donde viaja la información entre el usuario y el sistema.

**Proveedor de identidades:** El sistema que verifica quién es el usuario antes de dejarlo entrar (Active Directory).

**API Gateway:** La puerta de entrada única al sistema.

**Catálogo de notificaciones:** Guarda las plantillas de los mensajes y alertas que el sistema envía.

**Componente de notificaciones:** El servicio que entrega efectivamente las notificaciones (Brevo).

**Base de datos NoSQL:** Almacena los registros de auditoría del sistema de forma inmutable (MongoDB).

**Base de datos SQL:** El almacenamiento principal del sistema (PostgreSQL).

**Caché:** Guarda temporalmente en memoria los datos que se consultan con mucha frecuencia (Redis).

**Baúl de secretos:** Guarda de forma segura las contraseñas y claves (HashiCorp Vault).

**Firewall de aplicaciones web:** Filtra el tráfico que llega al sistema (ModSecurity + OWASP CRS).

**Catálogo de mensajes:** Centraliza los textos y etiquetas de la interfaz (Strapi).

**Catálogo de parámetros:** Almacena los valores configurables del laboratorio (Strapi).

**HIS/LIS:** El sistema del hospital desde donde llegan las órdenes de análisis.

---

## Arquetipo de solución/referencia

Un arquetipo de solución es una plantilla conceptual que describe cómo se organiza internamente un sistema para resolver un tipo de problema específico.

### Componentes

Los componentes se dividen en dos tipos:

- **Componentes propios:** Frontend y Backend de MicroRM
- **Componentes adaptados:** Firewall, API Gateway, bases de datos, caché, etc.

#### Componente 1: Dispositivos de acceso: PC y Laptop

Son los equipos desde los cuales el personal del laboratorio accede al sistema.

#### Componente 2: Canal de comunicación: Internet / Intranet hospitalaria

Es la red a través de la cual los dispositivos se comunican con el sistema. Todas las conexiones se establecen mediante HTTPS.

#### Componente 3: Componente propio: FrontEnd MicroRM

Es la capa visual e interactiva del sistema, desarrollada como una Single Page Application (SPA) con Vue 3.

#### Componente 4: Componente propio: Backend MicroRM

Es el cerebro operativo del sistema, desarrollado con Spring Boot 7.x e implementando una arquitectura hexagonal.

#### Componente 5: Componente adaptado: Firewall de aplicaciones web

ModSecurity + OWASP CRS. Primera línea de defensa del sistema.

#### Componente 6: Componente adaptado: API Gateway

Spring Cloud Gateway 4.x. Punto de entrada único para todas las peticiones.

#### Componente 7: Componente adaptado: Proveedor de identidades

Active Directory + Spring Security LDAP. Gestiona autenticación y autorización del personal.

#### Componente 8: Componente adaptado: HIS/LIS

Sistema de información hospitalario. Se integra mediante HAPI FHIR 7.x.

#### Componente 9: Componente adaptado: Catálogo de notificaciones

Strapi 5.x. Gestiona plantillas de alertas clínicas.

#### Componente 10: Componente adaptado: Catálogo de plantillas

Strapi 5.x. Gestiona formatos estandarizados de informes microbiológicos.

#### Componente 11: Componente adaptado: Catálogo de parámetros

Strapi 5.x. Almacena valores configurables del laboratorio (medios de cultivo, parámetros operativos).

#### Componente 12: Componente adaptado: Baúl de secretos

HashiCorp Vault 1.17.x. Almacena credenciales y claves de forma segura.

#### Componente 13: Componente adaptado: Base de datos relacional

PostgreSQL 16.x. Fuente de verdad del sistema. Almacena muestras, cultivos, resultados, usuarios.

#### Componente 14: Componente adaptado: Caché

Redis 7.x. Almacena temporalmente datos de consulta frecuente.

#### Componente 16: Componente adaptado: Change Data Capture

Apache Kafka 3.7.x + Debezium 2.7.x. Implementa captura de cambios asíncrona.

#### Componente 17: Componente adaptado: CI/CD Pipeline

GitHub Actions. Automatiza compilación, pruebas y despliegue.

#### Componente 18: Componente adaptado: Base de datos no relacional

MongoDB 7.x. Almacena registro inmutable de auditoría en modo append-only.

#### Componente 15: Componente adaptado: Servicio de notificaciones

Brevo v3. Entrega de alertas clínicas (emails, notificaciones).

---

## Arquitectura de solución/referencia

La arquitectura de solución es el diseño técnico concreto y detallado del sistema, donde cada componente tiene un nombre, una versión y una justificación específica.

### Tech Stack Confirmado

**Backend:**
- Java 26.0.1
- Spring 7.x
- Spring Cloud Gateway 4.x
- Spring Security + LDAP
- HAPI FHIR 7.x
- MapStruct 1.6.x
- PostgreSQL 16.x
- MongoDB 7.x
- Redis 7.x
- Apache Kafka 3.7.x + Debezium 2.7.x
- HashiCorp Vault 1.17.x
- Brevo v3
- GitHub Actions

**Frontend:**
- Vue 3.5.33
- TypeScript 5.9.3
- vue-i18n 11.x
- Vite 6.x
- Axios 1.x
- Pinia 2.x

**Seguridad:**
- ModSecurity + OWASP CRS
- OWASP HTML Sanitizer
- OWASP ZAP

**Desarrollo:**
- IntelliJ IDEA 2026.1
- VS Code 1.117.0
- SonarQube
- GitHub

---

## Línea base arquitectónica

Una línea base arquitectónica es el punto de partida formal y acordado de la arquitectura de un sistema. Es la fotografía de la arquitectura en un momento específico del proyecto.

### Línea base arquitectónica de componentes

La línea base arquitectónica define las decisiones de diseño que fueron analizadas, justificadas y acordadas, y que a partir de ese momento sirven como punto de referencia para el desarrollo.

#### Componente 1: Frontend MicroRM

**Decisiones tecnológicas:**

| Decisión | Valor |
| --- | --- |
| Lenguaje | TypeScript 5.9.3 |
| Framework UI | Vue 3.5.33 |
| Internacionalización | vue-i18n 11.x |
| Enrutamiento | Vue Router 4.x |
| Estado global | Pinia |
| Cliente HTTP | Axios |
| Protocolo | HTTPS / REST |
| Notificaciones en tiempo real | WebSocket |
| Entorno de desarrollo | Visual Studio Code 1.117.0 |

**Restricciones de diseño:**
- Ningún valor del dominio microbiológico puede estar hardcodeado
- Los textos deben estar centralizados en vue-i18n
- Ninguna lógica de negocio en el frontend
- Todas las rutas protegidas mediante guards

#### Componente 2: Backend MicroRM

**Estilo arquitectónico:** Monolito con Arquitectura Hexagonal (Ports & Adapters)

**Zonas:**

| Zona | Responsabilidad |
| --- | --- |
| **Dominio** | Entidades del negocio, reglas de dominio, puertos |
| **Aplicación** | Casos de uso, interactores, DTOs, mappers |
| **Infraestructura** | Adaptadores de entrada (REST) y salida (repositorios, integraciones) |

### Estilos y patrones arquitectónicos adoptados

#### Estilo 1: ModSecurity + OWASP CRS

**Nombre:** Firewall de aplicaciones web

**Problema:** El sistema maneja datos clínicos sensibles que pueden ser comprometidos por ataques de inyección SQL, XSS o accesos no autorizados.

**Solución:** Se adopta ModSecurity con OWASP Core Rule Set como primera capa de defensa perimetral, inspeccionando todo el tráfico antes de que alcance el API Gateway.

#### Estilo 2: Spring Cloud Gateway

**Nombre:** API Gateway

**Problema:** Sin un punto de entrada centralizado, cada módulo tendría que gestionar de forma independiente la autenticación y rate limiting.

**Solución:** Se adopta Spring Cloud Gateway como el único punto de acceso al backend, validando el token de sesión y centralizando el enrutamiento.

#### Estilo 3: Active Directory + Spring Security

**Nombre:** Autenticación y autorización

**Problema:** El sistema debe controlar estrictamente qué acciones puede ejecutar cada usuario según su rol clínico.

**Solución:** Se integra el Active Directory hospitalario mediante Spring Security LDAP, reutilizando las credenciales existentes.

#### Estilo 4: HAPI FHIR

**Nombre:** Integración clínica

**Problema:** El laboratorio recibe órdenes del HIS y debe devolver resultados sin generar dependencia de protocolos propietarios.

**Solución:** Se adopta HAPI FHIR como librería de integración nativa en Java, implementando el estándar HL7 FHIR.

#### Estilo 5: Strapi

**Nombre:** Gestión de catálogos

**Problema:** Los catálogos pueden cambiar con el tiempo y tenerlos embebidos en el código obliga a redespliegues ante cada modificación.

**Solución:** Se adopta Strapi Community Edition auto-hospedado para gestionar notificaciones, plantillas y parámetros.

#### Estilo 6: HashiCorp Vault

**Nombre:** Gestión de secretos

**Problema:** Almacenar credenciales directamente en el código es un riesgo de seguridad inaceptable.

**Solución:** Se adopta HashiCorp Vault Community Edition instalado on-premise en el servidor Windows.

#### Estilo 7: PostgreSQL

**Nombre:** Base de datos relacional

**Problema:** Los datos clínicos tienen relaciones complejas y requieren garantías de consistencia estrictas.

**Solución:** Se adopta PostgreSQL 16 con garantías ACID, instalado directamente en el servidor on-premise.

#### Estilo 8: Redis

**Nombre:** Almacenamiento en memoria

**Problema:** Las consultas repetitivas impactan sobre las bases de datos principales durante turnos de alta carga.

**Solución:** Se adopta Redis 7 Community Edition para almacenar en memoria datos de consulta frecuente.

#### Estilo 9: Apache Kafka

**Nombre:** Broker de eventos

**Problema:** Cuando ocurre un evento clínico, múltiples sistemas necesitan ser notificados de forma asíncrona.

**Solución:** Se adopta Apache Kafka 3.13 como broker de eventos central con Debezium para CDC.

#### Estilo 10: GitHub Actions

**Nombre:** CI/CD Pipeline

**Problema:** El sistema requiere entregas rápidas y controladas sin errores manuales.

**Solución:** Se adopta GitHub Actions con pipeline que ejecuta pruebas, análisis y despliegue automático.

#### Estilo 11: MongoDB

**Nombre:** Base de datos documental

**Problema:** Los registros de auditoría tienen estructura variable y deben ser inmutables.

**Solución:** Se adopta MongoDB 7 Community Edition en modo append-only para almacenar eventos.

#### Estilo 12: Brevo

**Nombre:** Servicio de notificaciones

**Problema:** El backend necesita enviar alertas sin gestionar complejidad de servidores de correo.

**Solución:** Se adopta Brevo API v3 con capa gratuita permanente de 300 emails diarios.

---

## Justificación alternativa de solución

La alternativa de solución propuesta para MicroRM fue seleccionada porque permite responder de manera adecuada a los requerimientos funcionales y no funcionales identificados durante el análisis, especialmente aquellos relacionados con seguridad, trazabilidad y cumplimiento normativo.

### Ventajas

- **Escalabilidad:** La arquitectura permite incorporar nuevos módulos y funcionalidades sin reconstruir completamente el sistema
- **Mantenibilidad:** La separación de responsabilidades entre componentes facilita corrección de errores y actualización de funcionalidades
- **Seguridad:** Autenticación, control de acceso por roles y protección de datos clínicos según Ley 1581
- **Trazabilidad de procesos:** Seguimiento completo de muestras desde recepción hasta resultado final
- **Centralización de la información:** Una plataforma unificada que reduce errores manuales
- **Facilidad de integración:** Puede integrarse con otros sistemas institucionales mediante FHIR
- **Generación de reportes:** Reportes estructurados para control y auditorías
- **Adaptabilidad:** Puede ajustarse a nuevos requerimientos sin afectar la estructura principal

### Desventajas

- **Mayor tiempo inicial de desarrollo:** La arquitectura organizada requiere más tiempo de planificación
- **Curva de aprendizaje:** El equipo debe comprender correctamente las tecnologías y patrones utilizados
- **Dependencia tecnológica:** La solución depende de tecnologías específicas que pueden requerir mantenimiento
- **Complejidad de configuración:** La integración de componentes de seguridad aumenta la complejidad inicial
- **Costos de infraestructura futuros:** Un crecimiento del laboratorio podría requerir mayores recursos
- **Necesidad de mantenimiento continuo:** El sistema requerirá monitoreo, actualización de dependencias y mantenimiento preventivo

---

## Vistas de arquitectura del sistema

### Vista Funcional/Casos de Uso

Define qué funcionalidades críticas implementa el sistema y cómo satisfacen las necesidades del laboratorio.

### Vista Lógica

Muestra la organización interna de componentes, capas y responsabilidades del sistema.

### Vista de Despliegue/Desarrollo/Implementación

Describe cómo se distribuyen los componentes en la infraestructura física del hospital.

---

## Diagrama de componentes

### Frontend MicroRM

**Componentes nucleares:**

1. **Runtime:** Node.js 22.x
2. **Bundler:** Vite 6.x
3. **Framework:** Vue 3.5.33
4. **Lenguaje:** TypeScript 5.9.3
5. **Enrutador:** Vue Router 4.x
6. **Estado Global:** Pinia 2.x
7. **Cliente HTTP:** Axios 1.x
8. **Editor:** VS Code 1.117.0

### Backend MicroRM

**Stack de desarrollo:**

1. **Lenguaje:** Java 26.0.1
2. **Framework:** Spring Framework 7.x
3. **IDE:** IntelliJ IDEA 2026.1
4. **Mapeo de datos:** MapStruct 1.6.x
5. **Análisis de código:** SonarQube
6. **Seguridad:** OWASP ZAP
7. **Versionamiento:** GitHub
8. **CI/CD:** GitHub Actions

**Dependencias de infraestructura:**

- PostgreSQL 16.x (base de datos relacional)
- MongoDB 7.x (auditoría inmutable)
- Redis 7.x (caché)
- Apache Kafka 3.7.x (eventos asíncrónos)
- Debezium 2.7.x (CDC)
- HashiCorp Vault 1.17.x (secretos)
- ModSecurity + OWASP CRS (firewall)
- Spring Cloud Gateway 4.x (API Gateway)
- HAPI FHIR 7.x (integración clínica)
- Strapi 5.x (catálogos)
- Brevo v3 (notificaciones)

---

## Estructura de paquetes

### Backend

Organizado bajo el paquete principal `co.edu.uco.microRM`:

- **controller:** Recibe y gestiona solicitudes HTTP
- **service:** Contiene la lógica de negocio del laboratorio
- **repository:** Acceso y persistencia en PostgreSQL y MongoDB
- **domain:** Modelo de datos y entidades
- **infrastructure:** Configuración técnica, seguridad y utilidades
- **exception:** Excepciones personalizadas para manejo de errores

### Frontend

Organizado en carpetas:

- **components:** Componentes reutilizables (formularios, tablas, modales)
- **views:** Páginas principales del sistema
- **stores:** Estado global mediante Pinia
- **services:** Comunicación con el backend vía REST
- **utils:** Funciones auxiliares reutilizables
- **assets:** Recursos estáticos (imágenes, íconos, CSS)
- **router:** Configuración de navegación y rutas protegidas
- **App.vue:** Componente raíz
- **main.js:** Punto de entrada

---

*Documento de arquitectura de software MicroRM - Versión 1.0*
*Generado el 19 de mayo de 2026*
