Documento de Arquitectura de Software (DAS)


**Proyecto**

<MICRORM>

**Arquitectos**

<Dany Alexander Cardona Gómez>







# <a name="_toc149220449"></a>Control de cambios y revisiones

|**Versión**|**Fecha**|**Tipo**|**Descripción**|**Autor**|
| - | - | - | - | - |
|**1**|19/05/2026|Creación|Versión inicial del documento|Dany Alexander Cardona Gómez|























# Contenido
[Control de cambios y revisiones	2](#_toc149220449)

[1.	(NO) Propósito del proyecto	14](#_toc149220450)

[2.	Motivadores de la arquitectura	14](#_toc149220451)

[2.1	Restricciones técnicas	14](#_toc149220452)

[2.2	Restricciones de negocio	14](#_toc149220453)

[2.3	Atributos de calidad	14](#_toc149220454)

[2.3.1	Atributo calidad 1	14](#_toc149220455)

[2.3.1.1	Característica 1	14](#_toc149220456)

[2.3.1.1.1	Escenario de calidad 1	14](#_toc149220457)

[2.3.1.1.2	Escenario de calidad 2	14](#_toc149220458)

[2.3.1.1.3	Escenario de calidad 3	14](#_toc149220459)

[2.3.1.1.N Escenario de calidad N	14](#_toc149220460)

[2.3.1.2	Característica 2	15](#_toc149220461)

[2.3.1.2.1	Escenario de calidad 1	15](#_toc149220462)

[2.3.1.2.2	Escenario de calidad 2	15](#_toc149220463)

[2.3.1.2.3	Escenario de calidad 3	15](#_toc149220464)

[2.3.1.2.N Escenario de calidad N	15](#_toc149220465)

[2.3.2	Atributo calidad 2	15](#_toc149220466)

[2.3.2.1	Característica 1	15](#_toc149220467)

[2.3.2.1.1	Escenario de calidad 1	15](#_toc149220468)

[2.3.2.1.2	Escenario de calidad 2	15](#_toc149220469)

[2.3.2.1.3	Escenario de calidad 3	15](#_toc149220470)

[2.3.2.1.N Escenario de calidad N	15](#_toc149220806)

[2.3.2.2	Característica 2	15](#_toc149220807)

[2.3.2.2.1	Escenario de calidad 1	16](#_toc149220808)

[2.3.2.2.2	Escenario de calidad 2	16](#_toc149220809)

[2.3.2.2.3	Escenario de calidad 3	16](#_toc149220810)

[2.3.2.1.N Escenario de calidad N	16](#_toc149220811)

[2.4	Funcionalidades críticas	16](#_toc149220812)

[3.	Tácticas y estrategias	16](#_toc149220813)

[4.	Modelo de contexto	16](#_toc149220814)

[5.	Arquetipo de solución/referencia	16](#_toc149220815)

[6.	Arquitectura de solución/referencia	16](#_toc149220816)

[7.	Línea base arquitectónica	16](#_toc149220817)

[7.1	Línea base arquitectónica de componentes	17](#_toc149220818)

[7.1.1	Componente 1	17](#_toc149220819)

[7.1.2	Componente 1	17](#_toc149220820)

[7.2	Estilos y patrones arquitectónicos adoptados	17](#_toc149220821)

[7.2.1	Estilo arquitectónico 1	17](#_toc149220822)

[7.2.1.1	Nombre	17](#_toc149220823)

[7.2.1.2	Problema	17](#_toc149220824)

[7.2.1.3	Solución/Motivación	17](#_toc149220825)

[7.2.2	Estilo arquitectónico 2	17](#_toc149220826)

[7.2.2.1	Nombre	17](#_toc149220827)

[7.2.2.2	Problema	17](#_toc149220828)

[7.2.2.3	Solución/Motivación	17](#_toc149220829)

[7.2.N Estilo arquitectónico 2	17](#_toc149220830)

[7.2.N.1 Nombre	17](#_toc149220831)

[7.2.N.2 Problema	18](#_toc149220832)

[7.2.N.3 Solución/Motivación	18](#_toc149220833)

[8.	Justificación alternativa de solución	18](#_toc149220834)

[8.1	Justificación	18](#_toc149220835)

[8.2	Ventajas	18](#_toc149220836)

[8.3	Desventajas	18](#_toc149220837)

[9.	Vistas de arquitectura del sistema	18](#_toc149220838)

[9.1	(NO) Vista Funcional/Vista de Escenarios/Vista de Casos de Uso	18](#_toc149220839)

[9.1.1	Modelo de procesos del negocio	18](#_toc149220840)

[9.1.2	Modelado de dominio	18](#_toc149220841)

[9.1.3	Modelo de contextos	18](#_toc149220842)

[9.1.3.1	Diagrama	18](#_toc149220843)

[9.1.3.2	Documentación contextos	18](#_toc149220844)

[9.1.4	Modelo de mapeo de contextos	19](#_toc149220845)

[9.1.4.1	Diagrama	19](#_toc149220846)

[9.1.4.2	Documentación mapeo de contextos	19](#_toc149220847)

[9.1.5	Modelos de dominio	19](#_toc149220848)

[9.1.5.1	Contexto 1	19](#_toc149220849)

[9.1.5.2	Modelo anémico	19](#_toc149220850)

[9.1.5.3	Modelo enriquecido	19](#_toc149220851)

[9.1.5.4	Contexto 2	19](#_toc149220852)

[9.1.5.5	Modelo anémico	19](#_toc149220853)

[9.1.5.6	Modelo enriquecido	19](#_toc149220854)

[9.1.5.7	Contexto 3	19](#_toc149220855)

[9.1.5.8	Modelo anémico	20](#_toc149220856)

[9.1.5.9	Modelo enriquecido	20](#_toc149220857)

[9.1.5.10	Contexto N	20](#_toc149220858)

[9.1.5.11	Modelo anémico	20](#_toc149220859)

[9.1.5.12	Modelo enriquecido	20](#_toc149220860)

[9.1.6	Flujo de eventos/Event Storming	20](#_toc149220861)

[9.1.6.1	Diagrama	20](#_toc149220862)

[9.1.6.2	Especificación	20](#_toc149220863)

[9.1.7	Glosario de términos del negocio	20](#_toc149220864)

[9.1.8	Especificación de requisitos de software	20](#_toc149220865)

[9.1.8.1	Requisitos de usuario	20](#_toc149220866)

[9.1.8.2	Requisitos del sistema	21](#_toc149220867)

[9.1.8.2.1	Requisitos funcionales	21](#_toc149220868)

[9.1.8.2.2	Requisitos no funcionales	21](#_toc149220869)

[9.1.8.2.3	Requisitos de información	21](#_toc149220870)

[9.1.8.2.4	Reglas de negocio	21](#_toc149220871)

[9.1.9	Casos de uso	21](#_toc149220872)

[9.1.9.1	Modelo de contexto	21](#_toc149220873)

[9.1.9.1.1	Diagrama	21](#_toc149220874)

[9.1.9.1.2	Descripción	21](#_toc149220875)

[9.1.9.2	Diagramas de casos de uso	21](#_toc149220876)

[9.1.9.2.1	Componente 1/Módulo 1/Grupo 1	21](#_toc149220877)

[9.1.9.2.1.1	Diagrama de casos de uso	22](#_toc149220878)

[9.1.9.2.1.2	Especificación de casos de uso	22](#_toc149220879)

[9.1.9.2.1.2.1	Caso de uso 1	22](#_toc149220880)

[9.1.9.2.1.2.1.1	Datos básicos caso de uso	22](#_toc149220881)

[9.1.9.2.1.2.1.2	Escenarios del caso de uso	22](#_toc149220882)

[9.1.9.2.1.2.1.3	Flujo normal/flujo básico	22](#_toc149220883)

[9.1.9.2.1.2.1.4	Flujo alterno 1	22](#_toc149220884)

[9.1.9.2.1.2.1.5	Flujo alterno 2	22](#_toc149220885)

[9.1.9.2.1.2.1.6	Flujo alterno N	22](#_toc149220886)

[9.1.9.2.1.2.1.7	Flujo Excepcional 1	22](#_toc149220887)

[9.1.9.2.1.2.1.8	Flujo Excepcional 2	22](#_toc149220888)

[9.1.9.2.1.2.1.9	Flujo Excepcional N	22](#_toc149220889)

[9.1.9.2.1.2.1.10	Diagrama de actividades	22](#_toc149220890)

[9.1.9.2.1.2.1.10.1	Diagrama	23](#_toc149220891)

[9.1.9.2.1.2.1.10.2	Documentación	23](#_toc149220892)

[9.1.9.2.1.2.1.11	Diagrama de estados	23](#_toc149220893)

[9.1.9.2.1.2.1.11.1	Diagrama	23](#_toc149220894)

[9.1.9.2.1.2.1.11.2	Documentación	23](#_toc149220895)

[9.1.9.2.1.2.2	Caso de uso 2	23](#_toc149220896)

[9.1.9.2.1.2.2.1	Datos básicos caso de uso	23](#_toc149220897)

[9.1.9.2.1.2.2.2	Escenarios del caso de uso	23](#_toc149220898)

[9.1.9.2.1.2.2.3	Flujo normal/flujo básico	23](#_toc149220899)

[9.1.9.2.1.2.2.4	Flujo alterno 1	23](#_toc149220900)

[9.1.9.2.1.2.2.5	Flujo alterno 2	23](#_toc149220901)

[9.1.9.2.1.2.2.6	Flujo alterno N	23](#_toc149220902)

[9.1.9.2.1.2.2.7	Flujo Excepcional 1	23](#_toc149220903)

[9.1.9.2.1.2.2.8	Flujo Excepcional 2	23](#_toc149220904)

[9.1.9.2.1.2.2.9	Flujo Excepcional N	24](#_toc149220905)

[9.1.9.2.1.2.2.10	Diagrama de actividades	24](#_toc149220906)

[9.1.9.2.1.2.2.10.1	Diagrama	24](#_toc149220907)

[9.1.9.2.1.2.2.10.2	Documentación	24](#_toc149220908)

[9.1.9.2.1.2.2.11	Diagrama de estados	24](#_toc149220909)

[9.1.9.2.1.2.2.11.1	Diagrama	24](#_toc149220910)

[9.1.9.2.1.2.2.11.2	Documentación	24](#_toc149220911)

[9.1.9.2.1.2.3	Caso de uso N	24](#_toc149220912)

[9.1.9.2.1.2.3.1	Datos básicos caso de uso	24](#_toc149220913)

[9.1.9.2.1.2.3.2	Escenarios del caso de uso	24](#_toc149220914)

[9.1.9.2.1.2.3.3	Flujo normal/flujo básico	24](#_toc149220915)

[9.1.9.2.1.2.3.4	Flujo alterno 1	24](#_toc149220916)

[9.1.9.2.1.2.3.5	Flujo alterno 2	24](#_toc149220917)

[9.1.9.2.1.2.3.6	Flujo alterno N	24](#_toc149220918)

[9.1.9.2.1.2.3.7	Flujo Excepcional 1	25](#_toc149220919)

[9.1.9.2.1.2.3.8	Flujo Excepcional 2	25](#_toc149220920)

[9.1.9.2.1.2.3.9	Flujo Excepcional N	25](#_toc149220921)

[9.1.9.2.1.2.3.10	Diagrama de actividades	25](#_toc149220922)

[9.1.9.2.1.2.3.10.1	Diagrama	25](#_toc149220923)

[9.1.9.2.1.2.3.10.2	Documentación	25](#_toc149220924)

[9.1.9.2.1.2.3.11	Diagrama de estados	25](#_toc149220925)

[9.1.9.2.1.2.3.11.1	Diagrama	25](#_toc149220926)

[9.1.9.2.1.2.3.11.2	Documentación	25](#_toc149220927)

[9.1.9.2.2	Componente 2/Módulo 2/Grupo 2	25](#_toc149220928)

[9.1.9.2.2.1	Diagrama de casos de uso	25](#_toc149220929)

[9.1.9.2.2.2	Especificación de casos de uso	25](#_toc149220930)

[9.1.9.2.2.2.1	Caso de uso 1	25](#_toc149220931)

[9.1.9.2.2.2.1.1	Datos básicos caso de uso	25](#_toc149220932)

[9.1.9.2.2.2.1.2	Escenarios del caso de uso	26](#_toc149220933)

[9.1.9.2.2.2.1.3	Flujo normal/flujo básico	26](#_toc149220934)

[9.1.9.2.2.2.1.4	Flujo alterno 1	26](#_toc149220935)

[9.1.9.2.2.2.1.5	Flujo alterno 2	26](#_toc149220936)

[9.1.9.2.2.2.1.6	Flujo alterno N	26](#_toc149220937)

[9.1.9.2.2.2.1.7	Flujo Excepcional 1	26](#_toc149220938)

[9.1.9.2.2.2.1.8	Flujo Excepcional 2	26](#_toc149220939)

[9.1.9.2.2.2.1.9	Flujo Excepcional N	26](#_toc149220940)

[9.1.9.2.2.2.1.10	Diagrama de actividades	26](#_toc149220941)

[9.1.9.2.2.2.1.10.1	Diagrama	26](#_toc149220942)

[9.1.9.2.2.2.1.10.2	Documentación	26](#_toc149220943)

[9.1.9.2.2.2.1.11	Diagrama de estados	26](#_toc149220944)

[9.1.9.2.2.2.1.11.1	Diagrama	26](#_toc149220945)

[9.1.9.2.2.2.1.11.2	Documentación	26](#_toc149220946)

[9.1.9.2.2.2.2	Caso de uso 2	27](#_toc149220947)

[9.1.9.2.2.2.2.1	Datos básicos caso de uso	27](#_toc149220948)

[9.1.9.2.2.2.2.2	Escenarios del caso de uso	27](#_toc149220949)

[9.1.9.2.2.2.2.3	Flujo normal/flujo básico	27](#_toc149220950)

[9.1.9.2.2.2.2.4	Flujo alterno 1	27](#_toc149220951)

[9.1.9.2.2.2.2.5	Flujo alterno 2	27](#_toc149220952)

[9.1.9.2.2.2.2.6	Flujo alterno N	27](#_toc149220953)

[9.1.9.2.2.2.2.7	Flujo Excepcional 1	27](#_toc149220954)

[9.1.9.2.2.2.2.8	Flujo Excepcional 2	27](#_toc149220955)

[9.1.9.2.2.2.2.9	Flujo Excepcional N	27](#_toc149220956)

[9.1.9.2.2.2.2.10	Diagrama de actividades	27](#_toc149220957)

[9.1.9.2.2.2.2.10.1	Diagrama	27](#_toc149220958)

[9.1.9.2.2.2.2.10.2	Documentación	27](#_toc149220959)

[9.1.9.2.2.2.2.11	Diagrama de estados	28](#_toc149220960)

[9.1.9.2.2.2.2.11.1	Diagrama	28](#_toc149220961)

[9.1.9.2.2.2.2.11.2	Documentación	28](#_toc149220962)

[9.1.9.2.2.2.3	Caso de uso N	28](#_toc149220963)

[9.1.9.2.2.2.3.1	Datos básicos caso de uso	28](#_toc149220964)

[9.1.9.2.2.2.3.2	Escenarios del caso de uso	28](#_toc149220965)

[9.1.9.2.2.2.3.3	Flujo normal/flujo básico	28](#_toc149220966)

[9.1.9.2.2.2.3.4	Flujo alterno 1	28](#_toc149220967)

[9.1.9.2.2.2.3.5	Flujo alterno 2	28](#_toc149220968)

[9.1.9.2.2.2.3.6	Flujo alterno N	28](#_toc149220969)

[9.1.9.2.2.2.3.7	Flujo Excepcional 1	28](#_toc149220970)

[9.1.9.2.2.2.3.8	Flujo Excepcional 2	28](#_toc149220971)

[9.1.9.2.2.2.3.9	Flujo Excepcional N	28](#_toc149220972)

[9.1.9.2.2.2.3.10	Diagrama de actividades	29](#_toc149220973)

[9.1.9.2.2.2.3.10.1	Diagrama	29](#_toc149220974)

[9.1.9.2.2.2.3.10.2	Documentación	29](#_toc149220975)

[9.1.9.2.2.2.3.11	Diagrama de estados	29](#_toc149220976)

[9.1.9.2.2.2.3.11.1	Diagrama	29](#_toc149220977)

[9.1.9.2.2.2.3.11.2	Documentación	29](#_toc149220978)

[9.1.9.2.3	Componente N/Módulo N/Grupo N	29](#_toc149220979)

[9.1.9.2.3.1	Diagrama de casos de uso	29](#_toc149220980)

[9.1.9.2.3.2	Especificación de casos de uso	29](#_toc149220981)

[9.1.9.2.3.2.1	Caso de uso 1	29](#_toc149220982)

[9.1.9.2.3.2.1.1	Datos básicos caso de uso	29](#_toc149220983)

[9.1.9.2.3.2.1.2	Escenarios del caso de uso	29](#_toc149220984)

[9.1.9.2.3.2.1.3	Flujo normal/flujo básico	29](#_toc149220985)

[9.1.9.2.3.2.1.4	Flujo alterno 1	29](#_toc149220986)

[9.1.9.2.3.2.1.5	Flujo alterno 2	30](#_toc149220987)

[9.1.9.2.3.2.1.6	Flujo alterno N	30](#_toc149220988)

[9.1.9.2.3.2.1.7	Flujo Excepcional 1	30](#_toc149220989)

[9.1.9.2.3.2.1.8	Flujo Excepcional 2	30](#_toc149220990)

[9.1.9.2.3.2.1.9	Flujo Excepcional N	30](#_toc149220991)

[9.1.9.2.3.2.1.10	Diagrama de actividades	30](#_toc149220992)

[9.1.9.2.3.2.1.10.1	Diagrama	30](#_toc149220993)

[9.1.9.2.3.2.1.10.2	Documentación	30](#_toc149220994)

[9.1.9.2.3.2.1.11	Diagrama de estados	30](#_toc149220995)

[9.1.9.2.3.2.1.11.1	Diagrama	30](#_toc149220996)

[9.1.9.2.3.2.1.11.2	Documentación	30](#_toc149220997)

[9.1.9.2.3.2.2	Caso de uso 2	30](#_toc149220998)

[9.1.9.2.3.2.2.1	Datos básicos caso de uso	30](#_toc149220999)

[9.1.9.2.3.2.2.2	Escenarios del caso de uso	30](#_toc149221000)

[9.1.9.2.3.2.2.3	Flujo normal/flujo básico	31](#_toc149221001)

[9.1.9.2.3.2.2.4	Flujo alterno 1	31](#_toc149221002)

[9.1.9.2.3.2.2.5	Flujo alterno 2	31](#_toc149221003)

[9.1.9.2.3.2.2.6	Flujo alterno N	31](#_toc149221004)

[9.1.9.2.3.2.2.7	Flujo Excepcional 1	31](#_toc149221005)

[9.1.9.2.3.2.2.8	Flujo Excepcional 2	31](#_toc149221006)

[9.1.9.2.3.2.2.9	Flujo Excepcional N	31](#_toc149221007)

[9.1.9.2.3.2.2.10	Diagrama de actividades	31](#_toc149221008)

[9.1.9.2.3.2.2.10.1	Diagrama	31](#_toc149221009)

[9.1.9.2.3.2.2.10.2	Documentación	31](#_toc149221010)

[9.1.9.2.3.2.2.11	Diagrama de estados	31](#_toc149221011)

[9.1.9.2.3.2.2.11.1	Diagrama	31](#_toc149221012)

[9.1.9.2.3.2.2.11.2	Documentación	31](#_toc149221013)

[9.1.9.2.3.2.3	Caso de uso N	31](#_toc149221014)

[9.1.9.2.3.2.3.1	Datos básicos caso de uso	32](#_toc149221015)

[9.1.9.2.3.2.3.2	Escenarios del caso de uso	32](#_toc149221016)

[9.1.9.2.3.2.3.3	Flujo normal/flujo básico	32](#_toc149221017)

[9.1.9.2.3.2.3.4	Flujo alterno 1	32](#_toc149221018)

[9.1.9.2.3.2.3.5	Flujo alterno 2	32](#_toc149221019)

[9.1.9.2.3.2.3.6	Flujo alterno N	32](#_toc149221020)

[9.1.9.2.3.2.3.7	Flujo Excepcional 1	32](#_toc149221021)

[9.1.9.2.3.2.3.8	Flujo Excepcional 2	32](#_toc149221022)

[9.1.9.2.3.2.3.9	Flujo Excepcional N	32](#_toc149221023)

[9.1.9.2.3.2.3.10	Diagrama de actividades	32](#_toc149221024)

[9.1.9.2.3.2.3.10.1	Diagrama	32](#_toc149221025)

[9.1.9.2.3.2.3.10.2	Documentación	32](#_toc149221026)

[9.1.9.2.3.2.3.11	Diagrama de estados	32](#_toc149221027)

[9.1.9.2.3.2.3.11.1	Diagrama	33](#_toc149221028)

[9.1.9.2.3.2.3.11.2	Documentación	33](#_toc149221029)

[9.1.10	Incepción Ágil	33](#_toc149221030)

[9.1.11	Por qué estamos aquí	33](#_toc149221031)

[9.1.12	Visión/Elevator Pitch	33](#_toc149221032)

[9.1.12.1	Visión	33](#_toc149221033)

[9.1.12.2	Project Canvas	33](#_toc149221034)

[9.1.12.3	Mapa de impacto	33](#_toc149221035)

[9.1.13	Caja de producto	33](#_toc149221036)

[9.1.14	Lo que sí, lo que no	33](#_toc149221037)

[9.1.14.1	Mapa de historias de usuario	33](#_toc149221038)

[9.1.14.2	Product Backlog Item	33](#_toc149221039)

[9.1.15	La comunidad	34](#_toc149221040)

[9.1.16	La solución	34](#_toc149221041)

[9.1.17	Los riesgos/Los miedos	34](#_toc149221042)

[9.1.18	Tamaño/Talla de historias de usuario	34](#_toc149221043)

[9.1.18.1	Tallaje del producto	34](#_toc149221044)

[9.1.18.1.1	Definiciones para el tallaje	34](#_toc149221045)

[9.1.18.1.2	Tallaje del producto	34](#_toc149221046)

[9.1.18.2	Release Plan	34](#_toc149221047)

[9.1.18.2.1	Definiciones para el release plan	34](#_toc149221048)

[9.1.18.2.2	Release plan	34](#_toc149221049)

[9.1.19	Trade off de atributos de calidad	34](#_toc149221050)

[9.1.20	Cuánto cuesta	34](#_toc149221051)

[9.1.20.1.1	Definiciones para el coste	35](#_toc149221052)

[9.1.20.1.2	Coste	35](#_toc149221053)

[9.2	Vista Lógica	35](#_toc149221054)

[9.2.1	(NO) Diagrama de clases	35](#_toc149221055)

[9.2.1.1	Componente 1	35](#_toc149221056)

[9.2.1.1.1	Diagrama	35](#_toc149221057)

[9.2.1.1.2	Documentación	35](#_toc149221058)

[9.2.1.2	Componente 2	35](#_toc149221059)

[9.2.1.2.1	Diagrama	35](#_toc149221060)

[9.2.1.2.2	Documentación	35](#_toc149221061)

[9.2.1.3	Componente N	35](#_toc149221062)

[9.2.1.3.1	Diagrama	35](#_toc149221063)

[9.2.1.3.2	Documentación	35](#_toc149221064)

[9.2.2	(NO) Diagrama de objetos	36](#_toc149221065)

[9.2.2.1	Componente 1	36](#_toc149221066)

[9.2.2.1.1	Diagrama	36](#_toc149221067)

[9.2.2.1.2	Documentación	36](#_toc149221068)

[9.2.2.2	Componente 2	36](#_toc149221069)

[9.2.2.2.1	Diagrama	36](#_toc149221070)

[9.2.2.2.2	Documentación	36](#_toc149221071)

[9.2.2.3	Componente N	36](#_toc149221072)

[9.3	Vista de Despliegue/Vista de Desarrollo/Vista de Implementación	36](#_toc149221073)

[9.3.1	Diagrama de componentes	36](#_toc149221074)

[9.3.1.1	Componente 1	36](#_toc149221075)

[9.3.1.1.1	Diagrama	36](#_toc149221076)

[9.3.1.1.2	Documentación	36](#_toc149221077)

[9.3.1.2	Componente 2	36](#_toc149221078)

[9.3.1.2.1	Diagrama	37](#_toc149221079)

[9.83.1.2.2	Documentación	37](#_toc149221080)

[9.3.1.3	Componente N	37](#_toc149221081)

[9.3.1.3.1	Diagrama	37](#_toc149221082)

[9.3.1.3.2	Documentación	37](#_toc149221083)

[9.3.2	Diagrama de paquetes	37](#_toc149221084)

[9.3.2.1	Componente 1	37](#_toc149221085)

[9.3.2.1.1	Diagrama	37](#_toc149221086)

[9.3.2.1.2	Documentación	37](#_toc149221087)

[9.3.2.2	Componente 2	37](#_toc149221088)

[9.3.2.2.1	Diagrama	37](#_toc149221089)

[9.3.2.2.2	Documentación	37](#_toc149221090)

[9.3.2.3	Componente N	37](#_toc149221091)

[9.3.2.3.1	Diagrama	37](#_toc149221092)

[9.3.2.3.2	Documentación	38](#_toc149221093)

[9.4	Vista de Procesos	38](#_toc149221094)

[9.4.1	Diagrama de secuencia	38](#_toc149221095)

[9.4.1.1	Componente 1	38](#_toc149221096)

[9.4.1.1.1	Diagrama	38](#_toc149221097)

[9.4.1.1.2	Documentación	38](#_toc149221098)

[9.4.1.2	Componente 2	38](#_toc149221099)

[9.4.1.2.1	Diagrama	38](#_toc149221100)

[9.4.1.2.2	Documentación	38](#_toc149221101)

[9.4.1.3	Componente N	38](#_toc149221102)

[9.4.1.3.1	Diagrama	38](#_toc149221103)

[9.4.1.3.2	Documentación	38](#_toc149221104)

[9.4.2	(NO*) Diagrama de colaboración	38](#_toc149221105)

[9.4.2.1	Componente 1	38](#_toc149221106)

[9.4.2.1.1	Diagrama	39](#_toc149221107)

[9.4.2.1.2	Documentación	39](#_toc149221108)

[9.4.2.2	Componente 2	39](#_toc149221109)

[9.4.2.2.1	Diagrama	39](#_toc149221110)

[9.4.2.2.2	Documentación	39](#_toc149221111)

[9.4.2.3	Componente N	39](#_toc149221112)

[9.4.2.3.1	Diagrama	39](#_toc149221113)

[9.4.2.3.2	Documentación	39](#_toc149221114)

[9.5	Vista Física/Vista de Implantación	39](#_toc149221115)

[9.5.1	Diagrama de despliegue	39](#_toc149221116)

[9.5.1.1	Diagrama	39](#_toc149221117)

[9.5.1.2	Documentación	39](#_toc149221118)
























1. # <a name="_toc149220450"></a>Propósito del proyecto
<Defina en términos comprensibles y de forma clara cuál es la visión del proyecto.>
1. # <a name="_toc149220451"></a>Motivadores de la arquitectura
<Los motivadores de la arquitectura son los factores o las necesidades funcionales de calidad, técnicas y de negocio que orientan, condicionan y justifican las decisiones de diseño de un sistema.\
Estos motivadores definen el rumbo del proyecto al delimitar su alcance, establecer las prioridades e identificar las restricciones que resultan no negociables para alcanzar la solución

Cada motivador influye directamente en una o varias decisiones de diseño, ya que determina aspectos como la estructura del sistema, las tecnologías seleccionadas, los patrones de diseño, la seguridad, el rendimiento, la escalabilidad y la mantenibilidad.>
1. ## <a name="_toc149220452"></a>Restricciones técnicas
<Defina en términos comprensibles qué son las restricciones técnicas, cómo le apuntan al desarrollo del proyecto y a la definición del diseño. De igual manera, referencie todas las restricciones técnicas que se tienen con su respectiva descripción y justificación.>
1. ## <a name="_toc149220453"></a>Restricciones de negocio
<Defina en términos comprensibles qué son las restricciones de negocio, cómo le apuntan al desarrollo del proyecto y a la definición del diseño. De igual manera, referencie todas las restricciones de negocio que se tienen con su respectiva descripción y justificación.>
1. ## <a name="_toc149220454"></a>Atributos de calidad
<Defina en términos comprensibles qué son los atributos de calidad, cómo le apuntan al desarrollo del proyecto y a la definición del diseño. De igual manera, referencie todos los atributos de calidad, características y escenarios que se tienen con su respectiva descripción y justificación.>
1. ## <a name="_toc149220455"></a>Atributo calidad 1
<Defina en términos comprensibles cuál es el atributo de calidad, cómo le apunta al desarrollo del proyecto y a la definición del diseño. >
1. ## ` `<a name="_toc149220456"></a>Característica 1
<Defina en términos comprensibles cuál es la característica asociada al atributo de calidad, cómo le apunta al desarrollo del proyecto y a la definición del diseño. >
1. ## <a name="_toc149220457"></a>Escenario de calidad 1
<Defina en términos comprensibles cuál es el escenario de calidad asociado a la característica del atributo de calidad, cómo le apunta al desarrollo del proyecto y a la definición del diseño. >
1. ## <a name="_toc149220458"></a>Escenario de calidad 2
<Defina en términos comprensibles cuál es el escenario de calidad asociado a la característica del atributo de calidad, cómo le apunta al desarrollo del proyecto y a la definición del diseño. >
1. ## <a name="_toc149220459"></a>Escenario de calidad 3
<Defina en términos comprensibles cuál es el escenario de calidad asociado a la característica del atributo de calidad, cómo le apunta al desarrollo del proyecto y a la definición del diseño. >
## <a name="_toc149220460"></a>2.3.1.1.N Escenario de calidad N
<Defina en términos comprensibles cuál es el escenario de calidad asociado a la característica del atributo de calidad, cómo le apunta al desarrollo del proyecto y a la definición del diseño. >
1. ## ` `<a name="_toc149220461"></a>Característica 2
<Defina en términos comprensibles cuál es la característica asociada al atributo de calidad, cómo le apunta al desarrollo del proyecto y a la definición del diseño. >
1. ## <a name="_toc149220462"></a>Escenario de calidad 1
<Defina en términos comprensibles cuál es el escenario de calidad asociado a la característica del atributo de calidad, cómo le apunta al desarrollo del proyecto y a la definición del diseño. >
1. ## <a name="_toc149220463"></a>Escenario de calidad 2
<Defina en términos comprensibles cuál es el escenario de calidad asociado a la característica del atributo de calidad, cómo le apunta al desarrollo del proyecto y a la definición del diseño. >
1. ## <a name="_toc149220464"></a>Escenario de calidad 3
<Defina en términos comprensibles cuál es el escenario de calidad asociado a la característica del atributo de calidad, cómo le apunta al desarrollo del proyecto y a la definición del diseño. >
## <a name="_toc149220465"></a>2.3.1.2.N Escenario de calidad N
<Defina en términos comprensibles cuál es el escenario de calidad asociado a la característica del atributo de calidad, cómo le apunta al desarrollo del proyecto y a la definición del diseño. >
1. ## <a name="_toc149220466"></a>Atributo calidad 2
<Defina en términos comprensibles cuál es el atributo de calidad, cómo le apunta al desarrollo del proyecto y a la definición del diseño. >
1. ## ` `<a name="_toc149220467"></a>Característica 1
<Defina en términos comprensibles cuál es la característica asociada al atributo de calidad, cómo le apunta al desarrollo del proyecto y a la definición del diseño. >
1. ## <a name="_toc149220468"></a>Escenario de calidad 1
<Defina en términos comprensibles cuál es el escenario de calidad asociado a la característica del atributo de calidad, cómo le apunta al desarrollo del proyecto y a la definición del diseño. >
1. ## <a name="_toc149220469"></a>Escenario de calidad 2
<Defina en términos comprensibles cuál es el escenario de calidad asociado a la característica del atributo de calidad, cómo le apunta al desarrollo del proyecto y a la definición del diseño. >
1. ## <a name="_toc149220470"></a>Escenario de calidad 3
<Defina en términos comprensibles cuál es el escenario de calidad asociado a la característica del atributo de calidad, cómo le apunta al desarrollo del proyecto y a la definición del diseño. >
## <a name="_toc149220806"></a>2.3.2.1.N Escenario de calidad N
<Defina en términos comprensibles cuál es el escenario de calidad asociado a la característica del atributo de calidad, cómo le apunta al desarrollo del proyecto y a la definición del diseño. >
1. ## ` `<a name="_toc149220807"></a>Característica 2
<Defina en términos comprensibles cuál es la característica asociada al atributo de calidad, cómo le apunta al desarrollo del proyecto y a la definición del diseño. >
1. ## <a name="_toc149220808"></a>Escenario de calidad 1
<Defina en términos comprensibles cuál es el escenario de calidad asociado a la característica del atributo de calidad, cómo le apunta al desarrollo del proyecto y a la definición del diseño. >
1. ## <a name="_toc149220809"></a>Escenario de calidad 2
<Defina en términos comprensibles cuál es el escenario de calidad asociado a la característica del atributo de calidad, cómo le apunta al desarrollo del proyecto y a la definición del diseño. >
1. ## <a name="_toc149220810"></a>Escenario de calidad 3
<Defina en términos comprensibles cuál es el escenario de calidad asociado a la característica del atributo de calidad, cómo le apunta al desarrollo del proyecto y a la definición del diseño. >
## <a name="_toc149220811"></a>2.3.2.1.N Escenario de calidad N
<Defina en términos comprensibles cuál es el escenario de calidad asociado a la característica del atributo de calidad, cómo le apunta al desarrollo del proyecto y a la definición del diseño. >
1. ## <a name="_toc149220812"></a>Funcionalidades críticas
<Defina en términos comprensibles qué son las funcionalidades críticas, cómo le apuntan al desarrollo del proyecto y a la definición del diseño. De igual manera, referencie todas las funcionalidades críticas que se tienen con su respectiva descripción y justificación.>
1. # <a name="_toc149220813"></a>Tácticas y estrategias
<Defina en términos comprensibles qué son las tácticas y estrategias. Adicionalmente se deben detallar qué tácticas y estrategias se analizaron para cumplir con las restricciones de diseño. De igual manera, para cada una su respectiva priorización, descripción y justificación.>
1. # <a name="_toc149220814"></a>Modelo de contexto
<Defina en términos comprensibles qué es un modelo de contexto. De igual manera, muestre el modelo de contexto, y el detalle que permita conocer la intención, motivación, uso y/o adopción de cada componente seleccionado de forma agnóstica con su respectiva justificación.>
1. # <a name="_toc149220815"></a>Arquetipo de solución/referencia
<Defina en términos comprensibles qué es un arquetipo de solución. De igual manera, muestre el arquetipo de solución, y el detalle que permita conocer la intención, motivación, uso y/o adopción de cada componente seleccionado de forma agnóstica con su respectiva justificación.>
1. # <a name="_toc149220816"></a>Arquitectura de solución/referencia
<Defina en términos comprensibles qué es una arquitectura de solución. De igual manera, muestre la arquitectura de solución, y el detalle que permita conocer la intención, motivación, uso y/o adopción de cada componente seleccionado de forma determinada (nombre del componente concreto) con su respectiva justificación.>
1. # <a name="_toc149220817"></a>Línea base arquitectónica
<Defina en términos comprensibles qué es una línea base arquitectónica.>
1. ## <a name="_toc149220818"></a>Línea base arquitectónica de componentes
<Detalle la línea base arquitectónica para cada uno de los componentes a desarrollar.>
1. ## <a name="_toc149220819"></a>Componente 1
<Muestre la línea base arquitectónica del componente en cuestión cada uno de los componentes a desarrollar y el detalle que permita conocer la intención, motivación, uso y/o adopción de cada elemento que lo conforma con su respectiva justificación.>
1. ## <a name="_toc149220820"></a>Componente 1
<Muestre la línea base arquitectónica del componente en cuestión cada uno de los componentes a desarrollar y el detalle que permita conocer la intención, motivación, uso y/o adopción de cada elemento que lo conforma con su respectiva justificación.>
1. ## <a name="_toc149220821"></a>Estilos y patrones arquitectónicos adoptados
<Defina en términos comprensibles qué son los estilos y patrones críticas, cómo le apuntan al desarrollo del proyecto y a la definición del diseño.>
1. ## <a name="_toc149220822"></a>Estilo arquitectónico 1
<Defina en términos comprensibles qué estilo arquitectónico es.>
1. ## ` `<a name="_toc149220823"></a>Nombre
<Nombre del estilo o patrón arquitectónico.>
1. ## ` `<a name="_toc149220824"></a>Problema
<Problema que resuelve el estilo o patrón arquitectónico y su contexto de aplicación.>
1. ## ` `<a name="_toc149220825"></a>Solución/Motivación
<Solución propuesta por el estilo o patrón arquitectónico.>
1. ## <a name="_toc149220826"></a>Estilo arquitectónico 2
<Defina en términos comprensibles qué estilo arquitectónico es.>
1. ## ` `<a name="_toc149220827"></a>Nombre
<Nombre del estilo o patrón arquitectónico.>
1. ## ` `<a name="_toc149220828"></a>Problema
<Problema que resuelve el estilo o patrón arquitectónico y su contexto de aplicación.>
1. ## ` `<a name="_toc149220829"></a>Solución/Motivación
<Solución propuesta por el estilo o patrón arquitectónico.>
## <a name="_toc149220830"></a>7.2.N Estilo arquitectónico 2
<Defina en términos comprensibles qué estilo arquitectónico es.>
## <a name="_toc149220831"></a>7.2.N.1 Nombre
<Nombre del estilo o patrón arquitectónico.>
## <a name="_toc149220832"></a>7.2.N.2 Problema
<Problema que resuelve el estilo o patrón arquitectónico y su contexto de aplicación.>
## <a name="_toc149220833"></a>7.2.N.3 Solución/Motivación
<Solución propuesta por el estilo o patrón arquitectónico.>
1. # <a name="_toc149220834"></a>Justificación alternativa de solución
<Defina en términos comprensibles qué es una justificación de una alternativa de solución.>
1. ## <a name="_toc149220835"></a>Justificación
<Justifique de forma clara y concreta por qué la alternativa de solución propuesta es la que mejor soluciona el problema propuesto, basado en las restricciones de diseño identificadas.>
1. ## <a name="_toc149220836"></a>Ventajas
<Describa cada una de las ventajas de la alternativa de solución seleccionada.>
1. ## <a name="_toc149220837"></a>Desventajas
<Describa cada una de las desventajas de la alternativa de solución seleccionada.>
1. # <a name="_toc149220838"></a>Vistas de arquitectura del sistema
<Defina en términos comprensibles qué son las vistas de diseño del sistema.>
1. ## <a name="_toc149220839"></a>Vista Funcional/Vista de Escenarios/Vista de Casos de Uso
<Defina en términos comprensibles qué es una vista funcional, cuál es su motivación y qué le aporta al diseño del sistema.>
1. ## <a name="_toc149220840"></a>Modelo de procesos del negocio
<Defina en términos comprensibles qué es un modelo de procesos de negocio. De igual manera, muestre el modelo de procesos de negocio, y el detalle que permita conocer la intención y motivación, con su respectiva justificación.>
1. ## <a name="_toc149220841"></a>Modelado de dominio
<Defina en términos comprensibles qué es un modelado de dominio. >
1. ## <a name="_toc149220842"></a>Modelo de contextos
<Defina en términos comprensibles qué es un modelo de contextos acotados. >
1. ## <a name="_toc149220843"></a>Diagrama
<Muestre el modelo de contextos, y el detalle que permita conocer la intención y motivación, con su respectiva justificación.>
1. ## <a name="_toc149220844"></a>Documentación contextos
<Defina una descripción y documentación para cada uno de los contextos, como también para las relaciones con el resto de los contextos, con el detalle que permita conocer la intención y motivación, con su respectiva justificación.>
1. ## <a name="_toc149220845"></a>Modelo de mapeo de contextos
<Defina en términos comprensibles qué es un modelo de mapeo de contextos acotados. >
1. ## <a name="_toc149220846"></a>Diagrama
<Muestre el modelo de mapeo de contextos, y el detalle que permita conocer la intención y motivación, con su respectiva justificación.>
1. ## <a name="_toc149220847"></a>Documentación mapeo de contextos
<Defina una descripción y documentación para cada uno de los contextos, como también para las relaciones clave para enlazarse con el resto de los contextos, con el detalle que permita conocer la intención y motivación, con su respectiva justificación.>
1. ## <a name="_toc149220848"></a>Modelos de dominio
<Defina en términos comprensibles qué es un modelo de dominio de contextos acotados, con el detalle que permita conocer la intención y motivación, con su respectiva justificación. >
1. ## <a name="_toc149220849"></a>Contexto 1
<Defina en términos comprensibles cuál es el problema acotado del contexto en cuestión, con el detalle que permita conocer la intención y motivación, con su respectiva justificación. >
1. ## <a name="_toc149220850"></a>Modelo anémico
<Muestre el modelo anémico del contexto, que permita comprender el problema.>
1. ## <a name="_toc149220851"></a>Modelo enriquecido
<Detalle el modelo enriquecido del contexto, que permita comprender el problema de forma clara, donde se puede ver la caracterización de cada objeto de dominio, con las demás características extendidas como combinaciones únicas, manejo de datos sensibles, responsabilidades con su respectiva documentación.>
1. ## <a name="_toc149220852"></a>Contexto 2
<Defina en términos comprensibles cuál es el problema acotado del contexto en cuestión, con el detalle que permita conocer la intención y motivación, con su respectiva justificación.>
1. ## <a name="_toc149220853"></a>Modelo anémico
<Muestre el modelo anémico del contexto, que permita comprender el problema.>
1. ## <a name="_toc149220854"></a>Modelo enriquecido
<Detalle el modelo enriquecido del contexto, que permita comprender el problema de forma clara, donde se puede ver la caracterización de cada objeto de dominio, con las demás características extendidas como combinaciones únicas, manejo de datos sensibles, responsabilidades con su respectiva documentación.>
1. ## <a name="_toc149220855"></a>Contexto 3
<Defina en términos comprensibles cuál es el problema acotado del contexto en cuestión, con el detalle que permita conocer la intención y motivación, con su respectiva justificación.>
1. ## <a name="_toc149220856"></a>Modelo anémico
<Muestre el modelo anémico del contexto, que permita comprender el problema.>
1. ## <a name="_toc149220857"></a>Modelo enriquecido
<Detalle el modelo enriquecido del contexto, que permita comprender el problema de forma clara, donde se puede ver la caracterización de cada objeto de dominio, con las demás características extendidas como combinaciones únicas, manejo de datos sensibles, responsabilidades con su respectiva documentación.>
1. ## <a name="_toc149220858"></a>Contexto N
<Defina en términos comprensibles cuál es el problema acotado del contexto en cuestión, con el detalle que permita conocer la intención y motivación, con su respectiva justificación.>
1. ## <a name="_toc149220859"></a>Modelo anémico
<Muestre el modelo anémico del contexto, que permita comprender el problema.>
1. ## <a name="_toc149220860"></a>Modelo enriquecido
<Detalle el modelo enriquecido del contexto, que permita comprender el problema de forma clara, donde se puede ver la caracterización de cada objeto de dominio, con las demás características extendidas como combinaciones únicas, manejo de datos sensibles, responsabilidades con su respectiva documentación.>
1. ## <a name="_toc149220861"></a>Flujo de eventos/<a name="_int_kluootm5"></a>Event Storming
<Defina en términos comprensibles qué es un flujo de eventos.>
1. ## <a name="_toc149220862"></a>Diagrama
<Muestre el flujo de eventos, y el detalle que permita conocer la intención y motivación.>
1. ## <a name="_toc149220863"></a>Especificación
<Muestre el detalle que permita conocer la intención y motivación, con su respectiva justificación, documentando claramente cada uno de los elementos que conforman cada evento.>
1. ## <a name="_toc149220864"></a>Glosario de términos del negocio
<Defina en términos comprensibles cada uno de los términos comunes de negocio en orden alfabético.>
1. ## <a name="_toc149220865"></a>Especificación de requisitos de software
<Defina en términos comprensibles qué es una especificación de requisitos de software, cómo le apuntan al desarrollo del proyecto y a la definición del diseño.>
1. ## <a name="_toc149220866"></a>Requisitos de usuario
<Defina en términos comprensibles qué son los requisitos de usuario, cómo le apuntan al desarrollo del proyecto y a la definición del diseño. De igual manera, referencie todos los requisitos de usuario con toda su caracterización, incluyendo el código que lo diferencia de los demás.>
1. ## <a name="_toc149220867"></a>Requisitos del sistema
<Defina en términos comprensibles qué son los requisitos del sistema, cómo le apuntan al desarrollo del proyecto y a la definición del diseño.>
1. ## <a name="_toc149220868"></a>Requisitos funcionales
<Defina en términos comprensibles qué son los requisitos funcionales, cómo le apuntan al desarrollo del proyecto y a la definición del diseño. De igual manera, referencie todos los requisitos funcionales con toda su caracterización, incluyendo el código que lo diferencia de los demás.>
1. ## <a name="_toc149220869"></a>Requisitos no funcionales
<Defina en términos comprensibles qué son los requisitos no funcionales, cómo le apuntan al desarrollo del proyecto y a la definición del diseño. De igual manera, referencie todos los requisitos no funcionales con toda su caracterización, incluyendo el código que lo diferencia de los demás.>
1. ## <a name="_toc149220870"></a>Requisitos de información
<Defina en términos comprensibles qué son los requisitos de información, cómo le apuntan al desarrollo del proyecto y a la definición del diseño. De igual manera, referencie todos los requisitos de información con toda su caracterización, incluyendo el código que lo diferencia de los demás.>
1. ## <a name="_toc149220871"></a>Reglas de negocio
<Defina en términos comprensibles qué son los requisitos no funcionales, cómo le apuntan al desarrollo del proyecto y a la definición del diseño. De igual manera, referencie todos los requisitos no funcionales con toda su caracterización, incluyendo el código que lo diferencia de los demás.>
1. ## <a name="_toc149220872"></a>Casos de uso
<Defina en términos comprensibles qué son los casos de uso, cómo le apuntan al desarrollo del proyecto y a la definición del diseño.>
1. ## <a name="_toc149220873"></a>Modelo de contexto
<Defina en términos comprensibles qué es un modelo de contexto, cómo le apuntan al desarrollo del proyecto y a la definición del diseño.>
1. ## <a name="_toc149220874"></a>Diagrama
<Muestre el diagrama de contexto de la solución.>
1. ## <a name="_toc149220875"></a>Descripción
<Defina en términos comprensibles los diferentes contextos, con sus respectivas relaciones y/o actores que intervienen en él.>
1. ## <a name="_toc149220876"></a>Diagramas de casos de uso
<Defina en términos comprensibles qué es un diagrama de casos de uso, cómo le apuntan al desarrollo del proyecto y a la definición del diseño.>
1. ## <a name="_toc149220877"></a>Componente 1/Módulo 1/Grupo 1
<Defina en términos comprensibles cuál es el contexto del componente, módulo y/o grupo en cuestión, con el detalle que permita conocer la intención y motivación, con su respectiva justificación.>
1. ## <a name="_toc149220878"></a>Diagrama de casos de uso
<Muestre el diagrama de casos del componente, módulo y/o grupo en cuestión.>
1. ## <a name="_toc149220879"></a>Especificación de casos de uso
<Defina en términos comprensibles qué es una especificación de casos de uso, cómo le apuntan al desarrollo del proyecto y a la definición del diseño.>
1. ## <a name="_toc149220880"></a>Caso de uso 1
<Defina a grandes rasgos, el objetivo del caso de uso.>
1. ## <a name="_toc149220881"></a>Datos básicos caso de uso
<Defina los datos básicos del caso de uso como identificador, nombre, descripción, actores, referencias cruzadas, precondiciones, post condiciones.>
1. ## <a name="_toc149220882"></a>Escenarios del caso de uso
<Defina en términos comprensibles qué es un escenario de un caso de uso.>
1. ## <a name="_toc149220883"></a>Flujo normal/flujo básico
<Defina el flujo básico del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220884"></a>Flujo alterno 1
<Defina el flujo alterno en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220885"></a>Flujo alterno 2
<Defina el flujo alterno en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220886"></a>Flujo alterno N
<Defina el flujo alterno en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220887"></a>Flujo Excepcional 1
<Defina el flujo excepcional en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220888"></a>Flujo Excepcional 2
<Defina el flujo excepcional en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220889"></a>Flujo Excepcional N
<Defina el flujo excepcional en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220890"></a>Diagrama de actividades
<Defina en términos comprensibles qué es un diagrama de actividades.>
1. ## <a name="_toc149220891"></a>Diagrama
<Muestre el diagrama de actividades que apalanca el caso de uso.>
1. ## <a name="_toc149220892"></a>Documentación
<Detalle cada actividad del diagrama de forma clara.>
1. ## <a name="_toc149220893"></a>Diagrama de estados
<Defina en términos comprensibles qué es un diagrama de estados.>
1. ## <a name="_toc149220894"></a>Diagrama
<Muestre el diagrama de estados que apalanca el caso de uso.>
1. ## <a name="_toc149220895"></a>Documentación
<Detalle cada estado y transición del diagrama de forma clara.>
1. ## <a name="_toc149220896"></a>Caso de uso 2
<Defina a grandes rasgos, el objetivo del caso de uso.>
1. ## <a name="_toc149220897"></a>Datos básicos caso de uso
<Defina los datos básicos del caso de uso como identificador, nombre, descripción, actores, referencias cruzadas, precondiciones, post condiciones.>
1. ## <a name="_toc149220898"></a>Escenarios del caso de uso
<Defina en términos comprensibles qué es un escenario de un caso de uso.>
1. ## <a name="_toc149220899"></a>Flujo normal/flujo básico
<Defina el flujo básico del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220900"></a>Flujo alterno 1
<Defina el flujo alterno en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220901"></a>Flujo alterno 2
<Defina el flujo alterno en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220902"></a>Flujo alterno N
<Defina el flujo alterno en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220903"></a>Flujo Excepcional 1
<Defina el flujo excepcional en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220904"></a>Flujo Excepcional 2
<Defina el flujo excepcional en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220905"></a>Flujo Excepcional N
<Defina el flujo excepcional en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220906"></a>Diagrama de actividades
<Defina en términos comprensibles qué es un diagrama de actividades.>
1. ## <a name="_toc149220907"></a>Diagrama
<Muestre el diagrama de actividades que apalanca el caso de uso.>
1. ## <a name="_toc149220908"></a>Documentación
<Detalle cada actividad del diagrama de forma clara.>
1. ## <a name="_toc149220909"></a>Diagrama de estados
<Defina en términos comprensibles qué es un diagrama de estados.>
1. ## <a name="_toc149220910"></a>Diagrama
<Muestre el diagrama de estados que apalanca el caso de uso.>
1. ## <a name="_toc149220911"></a>Documentación
<Detalle cada estado y transición del diagrama de forma clara.>
1. ## <a name="_toc149220912"></a>Caso de uso N
<Defina a grandes rasgos, el objetivo del caso de uso.>
1. ## <a name="_toc149220913"></a>Datos básicos caso de uso
<Defina los datos básicos del caso de uso como identificador, nombre, descripción, actores, referencias cruzadas, precondiciones, post condiciones.>
1. ## <a name="_toc149220914"></a>Escenarios del caso de uso
<Defina en términos comprensibles qué es un escenario de un caso de uso.>
1. ## <a name="_toc149220915"></a>Flujo normal/flujo básico
<Defina el flujo básico del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220916"></a>Flujo alterno 1
<Defina el flujo alterno en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220917"></a>Flujo alterno 2
<Defina el flujo alterno en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220918"></a>Flujo alterno N
<Defina el flujo alterno en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220919"></a>Flujo Excepcional 1
<Defina el flujo excepcional en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220920"></a>Flujo Excepcional 2
<Defina el flujo excepcional en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220921"></a>Flujo Excepcional N
<Defina el flujo excepcional en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220922"></a>Diagrama de actividades
<Defina en términos comprensibles qué es un diagrama de actividades.>
1. ## <a name="_toc149220923"></a>Diagrama
<Muestre el diagrama de actividades que apalanca el caso de uso.>
1. ## <a name="_toc149220924"></a>Documentación
<Detalle cada actividad del diagrama de forma clara.>
1. ## <a name="_toc149220925"></a>Diagrama de estados
<Defina en términos comprensibles qué es un diagrama de estados.>
1. ## <a name="_toc149220926"></a>Diagrama
<Muestre el diagrama de estados que apalanca el caso de uso.>
1. ## <a name="_toc149220927"></a>Documentación
<Detalle cada estado y transición del diagrama de forma clara.>
1. ## <a name="_toc149220928"></a>Componente 2/Módulo 2/Grupo 2
<Defina en términos comprensibles cuál es el contexto del componente, módulo y/o grupo en cuestión, con el detalle que permita conocer la intención y motivación, con su respectiva justificación.>
1. ## <a name="_toc149220929"></a>Diagrama de casos de uso
<Muestre el diagrama de casos del componente, módulo y/o grupo en cuestión.>
1. ## <a name="_toc149220930"></a>Especificación de casos de uso
<Defina en términos comprensibles qué es una especificación de casos de uso, cómo le apuntan al desarrollo del proyecto y a la definición del diseño.>
1. ## <a name="_toc149220931"></a>Caso de uso 1
<Defina a grandes rasgos, el objetivo del caso de uso.>
1. ## <a name="_toc149220932"></a>Datos básicos caso de uso
<Defina los datos básicos del caso de uso como identificador, nombre, descripción, actores, referencias cruzadas, precondiciones, post condiciones.>
1. ## <a name="_toc149220933"></a>Escenarios del caso de uso
<Defina en términos comprensibles qué es un escenario de un caso de uso.>
1. ## <a name="_toc149220934"></a>Flujo normal/flujo básico
<Defina el flujo básico del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220935"></a>Flujo alterno 1
<Defina el flujo alterno en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220936"></a>Flujo alterno 2
<Defina el flujo alterno en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220937"></a>Flujo alterno N
<Defina el flujo alterno en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220938"></a>Flujo Excepcional 1
<Defina el flujo excepcional en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220939"></a>Flujo Excepcional 2
<Defina el flujo excepcional en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220940"></a>Flujo Excepcional N
<Defina el flujo excepcional en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220941"></a>Diagrama de actividades
<Defina en términos comprensibles qué es un diagrama de actividades.>
1. ## <a name="_toc149220942"></a>Diagrama
<Muestre el diagrama de actividades que apalanca el caso de uso.>
1. ## <a name="_toc149220943"></a>Documentación
<Detalle cada actividad del diagrama de forma clara.>
1. ## <a name="_toc149220944"></a>Diagrama de estados
<Defina en términos comprensibles qué es un diagrama de estados.>
1. ## <a name="_toc149220945"></a>Diagrama
<Muestre el diagrama de estados que apalanca el caso de uso.>
1. ## <a name="_toc149220946"></a>Documentación
<Detalle cada estado y transición del diagrama de forma clara.>
1. ## <a name="_toc149220947"></a>Caso de uso 2
<Defina a grandes rasgos, el objetivo del caso de uso.>
1. ## <a name="_toc149220948"></a>Datos básicos caso de uso
<Defina los datos básicos del caso de uso como identificador, nombre, descripción, actores, referencias cruzadas, precondiciones, post condiciones.>
1. ## <a name="_toc149220949"></a>Escenarios del caso de uso
<Defina en términos comprensibles qué es un escenario de un caso de uso.>
1. ## <a name="_toc149220950"></a>Flujo normal/flujo básico
<Defina el flujo básico del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220951"></a>Flujo alterno 1
<Defina el flujo alterno en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220952"></a>Flujo alterno 2
<Defina el flujo alterno en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220953"></a>Flujo alterno N
<Defina el flujo alterno en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220954"></a>Flujo Excepcional 1
<Defina el flujo excepcional en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220955"></a>Flujo Excepcional 2
<Defina el flujo excepcional en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220956"></a>Flujo Excepcional N
<Defina el flujo excepcional en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220957"></a>Diagrama de actividades
<Defina en términos comprensibles qué es un diagrama de actividades.>
1. ## <a name="_toc149220958"></a>Diagrama
<Muestre el diagrama de actividades que apalanca el caso de uso.>
1. ## <a name="_toc149220959"></a>Documentación
<Detalle cada actividad del diagrama de forma clara.>
1. ## <a name="_toc149220960"></a>Diagrama de estados
<Defina en términos comprensibles qué es un diagrama de estados.>
1. ## <a name="_toc149220961"></a>Diagrama
<Muestre el diagrama de estados que apalanca el caso de uso.>
1. ## <a name="_toc149220962"></a>Documentación
<Detalle cada estado y transición del diagrama de forma clara.>
1. ## <a name="_toc149220963"></a>Caso de uso N
<Defina a grandes rasgos, el objetivo del caso de uso.>
1. ## <a name="_toc149220964"></a>Datos básicos caso de uso
<Defina los datos básicos del caso de uso como identificador, nombre, descripción, actores, referencias cruzadas, precondiciones, post condiciones.>
1. ## <a name="_toc149220965"></a>Escenarios del caso de uso
<Defina en términos comprensibles qué es un escenario de un caso de uso.>
1. ## <a name="_toc149220966"></a>Flujo normal/flujo básico
<Defina el flujo básico del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220967"></a>Flujo alterno 1
<Defina el flujo alterno en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220968"></a>Flujo alterno 2
<Defina el flujo alterno en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220969"></a>Flujo alterno N
<Defina el flujo alterno en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220970"></a>Flujo Excepcional 1
<Defina el flujo excepcional en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220971"></a>Flujo Excepcional 2
<Defina el flujo excepcional en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220972"></a>Flujo Excepcional N
<Defina el flujo excepcional en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220973"></a>Diagrama de actividades
<Defina en términos comprensibles qué es un diagrama de actividades.>
1. ## <a name="_toc149220974"></a>Diagrama
<Muestre el diagrama de actividades que apalanca el caso de uso.>
1. ## <a name="_toc149220975"></a>Documentación
<Detalle cada actividad del diagrama de forma clara.>
1. ## <a name="_toc149220976"></a>Diagrama de estados
<Defina en términos comprensibles qué es un diagrama de estados.>
1. ## <a name="_toc149220977"></a>Diagrama
<Muestre el diagrama de estados que apalanca el caso de uso.>
1. ## <a name="_toc149220978"></a>Documentación
<Detalle cada estado y transición del diagrama de forma clara.>
1. ## <a name="_toc149220979"></a>Componente N/Módulo N/Grupo N
<Defina en términos comprensibles cuál es el contexto del componente, módulo y/o grupo en cuestión, con el detalle que permita conocer la intención y motivación, con su respectiva justificación.>
1. ## <a name="_toc149220980"></a>Diagrama de casos de uso
<Muestre el diagrama de casos del componente, módulo y/o grupo en cuestión.>
1. ## <a name="_toc149220981"></a>Especificación de casos de uso
<Defina en términos comprensibles qué es una especificación de casos de uso, cómo le apuntan al desarrollo del proyecto y a la definición del diseño.>
1. ## <a name="_toc149220982"></a>Caso de uso 1
<Defina a grandes rasgos, el objetivo del caso de uso.>
1. ## <a name="_toc149220983"></a>Datos básicos caso de uso
<Defina los datos básicos del caso de uso como identificador, nombre, descripción, actores, referencias cruzadas, precondiciones, post condiciones.>
1. ## <a name="_toc149220984"></a>Escenarios del caso de uso
<Defina en términos comprensibles qué es un escenario de un caso de uso.>
1. ## <a name="_toc149220985"></a>Flujo normal/flujo básico
<Defina el flujo básico del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220986"></a>Flujo alterno 1
<Defina el flujo alterno en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220987"></a>Flujo alterno 2
<Defina el flujo alterno en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220988"></a>Flujo alterno N
<Defina el flujo alterno en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220989"></a>Flujo Excepcional 1
<Defina el flujo excepcional en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220990"></a>Flujo Excepcional 2
<Defina el flujo excepcional en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220991"></a>Flujo Excepcional N
<Defina el flujo excepcional en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149220992"></a>Diagrama de actividades
<Defina en términos comprensibles qué es un diagrama de actividades.>
1. ## <a name="_toc149220993"></a>Diagrama
<Muestre el diagrama de actividades que apalanca el caso de uso.>
1. ## <a name="_toc149220994"></a>Documentación
<Detalle cada actividad del diagrama de forma clara.>
1. ## <a name="_toc149220995"></a>Diagrama de estados
<Defina en términos comprensibles qué es un diagrama de estados.>
1. ## <a name="_toc149220996"></a>Diagrama
<Muestre el diagrama de estados que apalanca el caso de uso.>
1. ## <a name="_toc149220997"></a>Documentación
<Detalle cada estado y transición del diagrama de forma clara.>
1. ## <a name="_toc149220998"></a>Caso de uso 2
<Defina a grandes rasgos, el objetivo del caso de uso.>
1. ## <a name="_toc149220999"></a>Datos básicos caso de uso
<Defina los datos básicos del caso de uso como identificador, nombre, descripción, actores, referencias cruzadas, precondiciones, post condiciones.>
1. ## <a name="_toc149221000"></a>Escenarios del caso de uso
<Defina en términos comprensibles qué es un escenario de un caso de uso.>
1. ## <a name="_toc149221001"></a>Flujo normal/flujo básico
<Defina el flujo básico del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149221002"></a>Flujo alterno 1
<Defina el flujo alterno en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149221003"></a>Flujo alterno 2
<Defina el flujo alterno en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149221004"></a>Flujo alterno N
<Defina el flujo alterno en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149221005"></a>Flujo Excepcional 1
<Defina el flujo excepcional en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149221006"></a>Flujo Excepcional 2
<Defina el flujo excepcional en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149221007"></a>Flujo Excepcional N
<Defina el flujo excepcional en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149221008"></a>Diagrama de actividades
<Defina en términos comprensibles qué es un diagrama de actividades.>
1. ## <a name="_toc149221009"></a>Diagrama
<Muestre el diagrama de actividades que apalanca el caso de uso.>
1. ## <a name="_toc149221010"></a>Documentación
<Detalle cada actividad del diagrama de forma clara.>
1. ## <a name="_toc149221011"></a>Diagrama de estados
<Defina en términos comprensibles qué es un diagrama de estados.>
1. ## <a name="_toc149221012"></a>Diagrama
<Muestre el diagrama de estados que apalanca el caso de uso.>
1. ## <a name="_toc149221013"></a>Documentación
<Detalle cada estado y transición del diagrama de forma clara.>
1. ## <a name="_toc149221014"></a>Caso de uso N
<Defina a grandes rasgos, el objetivo del caso de uso.>
1. ## <a name="_toc149221015"></a>Datos básicos caso de uso
<Defina los datos básicos del caso de uso como identificador, nombre, descripción, actores, referencias cruzadas, precondiciones, post condiciones.>
1. ## <a name="_toc149221016"></a>Escenarios del caso de uso
<Defina en términos comprensibles qué es un escenario de un caso de uso.>
1. ## <a name="_toc149221017"></a>Flujo normal/flujo básico
<Defina el flujo básico del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149221018"></a>Flujo alterno 1
<Defina el flujo alterno en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149221019"></a>Flujo alterno 2
<Defina el flujo alterno en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149221020"></a>Flujo alterno N
<Defina el flujo alterno en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149221021"></a>Flujo Excepcional 1
<Defina el flujo excepcional en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149221022"></a>Flujo Excepcional 2
<Defina el flujo excepcional en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149221023"></a>Flujo Excepcional N
<Defina el flujo excepcional en cuestión del caso de uso, con los respectivos datos de nombre, descripción, tipo de flujo y pasos.>
1. ## <a name="_toc149221024"></a>Diagrama de actividades
<Defina en términos comprensibles qué es un diagrama de actividades.>
1. ## <a name="_toc149221025"></a>Diagrama
<Muestre el diagrama de actividades que apalanca el caso de uso.>
1. ## <a name="_toc149221026"></a>Documentación
<Detalle cada actividad del diagrama de forma clara.>
1. ## <a name="_toc149221027"></a>Diagrama de estados
<Defina en términos comprensibles qué es un diagrama de estados.>
1. ## <a name="_toc149221028"></a>Diagrama
<Muestre el diagrama de estados que apalanca el caso de uso.>
1. ## <a name="_toc149221029"></a>Documentación
<Detalle cada estado y transición del diagrama de forma clara.>
1. ## <a name="_toc149221030"></a>Incepción Ágil
<Defina en términos comprensibles cuál es el objetivo de un agile <a name="_int_0uzulufr"></a>inception y cómo le aporta al desarrollo del proyecto y a la definición del diseño.>
1. ## <a name="_toc149221031"></a>Por qué estamos aquí
<Defina en términos comprensibles qué por qué estamos aquí y cómo le aporta al desarrollo del proyecto y a la definición del diseño.>
1. ## <a name="_toc149221032"></a>Visión/<a name="_int_yvobmehg"></a>Elevator Pitch
<Defina en términos comprensibles qué es la visión y cómo le aporta al desarrollo del proyecto y a la definición del diseño.>
1. ## <a name="_toc149221033"></a>Visión
<Defina en términos comprensibles qué es la visión y cómo le aporta al desarrollo del proyecto y a la definición del diseño. Finalmente muestre la visión del proyecto.>
1. ## <a name="_toc149221034"></a>Project <a name="_int_0azjuku0"></a>Canvas
<Defina en términos comprensibles qué es el Project canvas y cómo le aporta al desarrollo del proyecto y a la definición del diseño. Finalmente muestre el Project canvas de.>
1. ## <a name="_toc149221035"></a>Mapa de impacto
<Defina en términos comprensibles qué es un mapa de impacto y cómo le aporta al desarrollo del proyecto y a la definición del diseño. Finalmente muestre el mapa de impacto del proyecto.>
1. ## <a name="_toc149221036"></a>Caja de producto
<Defina en términos comprensibles qué es la caja de producto y cómo le aporta al proyecto. Finalmente muestre la caja del producto.>
1. ## <a name="_toc149221037"></a>Lo que sí, lo que no
<Defina en términos comprensibles qué es la visión y cómo le aporta al desarrollo del proyecto y a la definición del diseño.>
1. ## <a name="_toc149221038"></a>Mapa de historias de usuario
<Defina en términos comprensibles qué es el mapa de historias de usuario y cómo le aporta al proyecto. Finalmente muestre el mapa de historias de usuario del producto.>
1. ## <a name="_int_cz1ibtpw"></a><a name="_toc149221039"></a>Product Backlog Item
<Defina en términos comprensibles qué es el product backlog item y cómo le aporta al proyecto. Finalmente muestre el product backlog item del producto.>
1. ## <a name="_toc149221040"></a>La comunidad
<Defina en términos comprensibles qué es la comunidad del proyecto y cómo le aporta al proyecto. Finalmente muestre la comunidad del proyecto.>
1. ## <a name="_toc149221041"></a>La solución
<Defina en términos comprensibles qué es la solución y cómo le aporta al proyecto. Finalmente muestre el modelo de contexto de solución, con su respectiva explicación.>
1. ## <a name="_toc149221042"></a>Los riesgos/Los miedos
<Defina en términos comprensibles qué es el mapa de historias de usuario y cómo le aporta al proyecto. Finalmente muestre el mapa de historias de usuario del producto.>
1. ## <a name="_toc149221043"></a>Tamaño/Talla de historias de usuario
<Defina en términos comprensibles qué es el tamaño y cómo le aporta al proyecto.>
1. ## <a name="_toc149221044"></a>Tallaje del producto
<Defina en términos comprensibles qué es el tallaje de historias de usuario y cómo le aporta al proyecto.>
1. ## <a name="_toc149221045"></a>Definiciones para el tallaje
<Defina claramente las condiciones definidas para el tallaje del producto como, que permita identificar a qué corresponde cada tamaño asignado.>
1. ## <a name="_toc149221046"></a>Tallaje del producto
<Muestre el tallaje de historias de usuario del producto.>
1. ## <a name="_int_kup0pgl7"></a><a name="_toc149221047"></a>Release Plan
<Defina en términos comprensibles qué es el Release Plan y cómo le aporta al proyecto.>
1. ## <a name="_toc149221048"></a>Definiciones para el release plan
<Defina claramente las condiciones definidas para el release plan del producto, que permita identificar tamaño del equipo de desarrollo, duración de los sprint y otros aspectos de interés para interpretarlo mejor.>
1. ## <a name="_toc149221049"></a>Release plan
<Muestre el plan de release del producto.>
1. ## <a name="_int_yp3afikx"></a><a name="_toc149221050"></a>Trade off de atributos de calidad
<Defina en términos comprensibles qué es el trade off de atributos de calidad y cómo le aporta al proyecto. Finalmente muestre el trade off y la explicación que permita interpretarlo de forma adecuada.>
1. ## <a name="_toc149221051"></a>Cuánto cuesta
<Defina en términos comprensibles qué es el costo del proyecto y cómo le aporta al proyecto. Finalmente muestre el mapa de historias de usuario del producto.>
1. ## <a name="_toc149221052"></a>Definiciones para el coste
<Defina claramente las condiciones definidas para el coste del producto, que permita identificar valor de las horas de las personas que conforman el equipo de desarrollo y otros aspectos de interés para interpretarlo mejor.>
1. ## <a name="_toc149221053"></a>Coste
<Muestre el coste detallado del proyecto.>
1. ## <a name="_toc149221054"></a>Vista Lógica
<Defina en términos comprensibles qué es una vista lógica, cuál es su motivación y qué le aporta al diseño del sistema.>
1. ## <a name="_toc149221055"></a>Diagrama de clases
<Defina en términos comprensibles qué es diagrama de clases, cuál es su motivación y qué le aporta al diseño del sistema.>
1. ## <a name="_toc149221056"></a>Componente 1
<Defina en términos comprensibles cuál es la motivación de este componente.>
1. ## <a name="_toc149221057"></a>Diagrama
<Muestre el diagrama de clases en cuestión.>
1. ## <a name="_toc149221058"></a>Documentación
<Muestre y/o detalle el diagrama de clases del componente en cuestión con la documentación respectiva.>
1. ## <a name="_toc149221059"></a>Componente 2
<Defina en términos comprensibles cuál es la motivación de este componente.>
1. ## <a name="_toc149221060"></a>Diagrama
<Muestre el diagrama de clases en cuestión.>
1. ## <a name="_toc149221061"></a>Documentación
<Muestre y/o detalle el diagrama de clases del componente en cuestión con la documentación respectiva.>
1. ## <a name="_toc149221062"></a>Componente N
<Defina en términos comprensibles cuál es la motivación de este componente.>
1. ## <a name="_toc149221063"></a>Diagrama
<Muestre el diagrama de clases en cuestión.>
1. ## <a name="_toc149221064"></a>Documentación
<Muestre y/o detalle el diagrama de clases del componente en cuestión con la documentación respectiva.>
1. ## <a name="_toc149221065"></a>Diagrama de objetos
<Defina en términos comprensibles qué es diagrama de objetos, cuál es su motivación y qué le aporta al diseño del sistema.>
1. ## <a name="_toc149221066"></a>Componente 1
<Defina en términos comprensibles cuál es la motivación de este componente.>
1. ## <a name="_toc149221067"></a>Diagrama
<Muestre el diagrama de objetos en cuestión.>
1. ## <a name="_toc149221068"></a>Documentación
<Muestre y/o detalle el diagrama de objetos del componente en cuestión con la documentación respectiva.>
1. ## <a name="_toc149221069"></a>Componente 2
<Defina en términos comprensibles cuál es la motivación de este componente.>
1. ## <a name="_toc149221070"></a>Diagrama
<Muestre el diagrama de objetos en cuestión.>
1. ## <a name="_toc149221071"></a>Documentación
<Muestre y/o detalle el diagrama de objetos del componente en cuestión con la documentación respectiva.>
1. ## <a name="_toc149221072"></a>Componente N
<Defina en términos comprensibles cuál es la motivación de este componente.>
1. ## <a name="_toc149221073"></a>Vista de Despliegue/Vista de Desarrollo/Vista de Implementación
<Defina en términos comprensibles qué es una vista de despliegue, cuál es su motivación y qué le aporta al diseño del sistema.>
1. ## <a name="_toc149221074"></a>Diagrama de componentes
<Defina en términos comprensibles qué es diagrama de componentes, cuál es su motivación y qué le aporta al diseño del sistema.>
1. ## <a name="_toc149221075"></a>Componente 1
<Defina en términos comprensibles cuál es la motivación de este componente.>
1. ## <a name="_toc149221076"></a>Diagrama
<Muestre el diagrama de componentes del componente en cuestión.>
1. ## <a name="_toc149221077"></a>Documentación
<Muestre y/o detalle el diagrama de componentes del componente en cuestión con la documentación respectiva.>
1. ## <a name="_toc149221078"></a>Componente 2
<Defina en términos comprensibles cuál es la motivación de este componente.>
1. ## <a name="_toc149221079"></a>Diagrama
<Muestre el diagrama de componentes del componente en cuestión.>
1. ## <a name="_toc149221080"></a>Documentación
<Muestre y/o detalle el diagrama de componentes del componente en cuestión con la documentación respectiva.>
1. ## <a name="_toc149221081"></a>Componente N
<Defina en términos comprensibles cuál es la motivación de este componente.>
1. ## <a name="_toc149221082"></a>Diagrama
<Muestre el diagrama de componentes del componente en cuestión.>
1. ## <a name="_toc149221083"></a>Documentación
<Muestre y/o detalle el diagrama de componentes del componente en cuestión con la documentación respectiva.>
1. ## <a name="_toc149221084"></a>Diagrama de paquetes
<Defina en términos comprensibles qué es diagrama de paquetes, cuál es su motivación y qué le aporta al diseño del sistema.>
1. ## <a name="_toc149221085"></a>Componente 1
<Defina en términos comprensibles cuál es la motivación de este componente.>
1. ## <a name="_toc149221086"></a>Diagrama
<Muestre el diagrama de paquetes en cuestión.>
1. ## <a name="_toc149221087"></a>Documentación
<Muestre y/o detalle el diagrama de paquetes del componente en cuestión con la documentación respectiva.>
1. ## <a name="_toc149221088"></a>Componente 2
<Defina en términos comprensibles cuál es la motivación de este componente.>
1. ## <a name="_toc149221089"></a>Diagrama
<Muestre el diagrama de paquetes en cuestión.>
1. ## <a name="_toc149221090"></a>Documentación
<Muestre y/o detalle el diagrama de paquetes del componente en cuestión con la documentación respectiva.>
1. ## <a name="_toc149221091"></a>Componente N
<Defina en términos comprensibles cuál es la motivación de este componente.>
1. ## <a name="_toc149221092"></a>Diagrama
<Muestre el diagrama de paquetes en cuestión.>
1. ## <a name="_toc149221093"></a>Documentación
<Muestre y/o detalle el diagrama de paquetes del componente en cuestión con la documentación respectiva.>
1. ## <a name="_toc149221094"></a>Vista de Procesos
<Defina en términos comprensibles qué es una vista de procesos, cuál es su motivación y qué le aporta al diseño del sistema.>
1. ## <a name="_toc149221095"></a>Diagrama de secuencia
<Defina en términos comprensibles qué es diagrama de secuencia, cuál es su motivación y qué le aporta al diseño del sistema.>
1. ## <a name="_toc149221096"></a>Componente 1
<Defina en términos comprensibles cuál es la motivación de este componente.>
1. ## <a name="_toc149221097"></a>Diagrama
<Muestre el diagrama de secuencia en cuestión.>
1. ## <a name="_toc149221098"></a>Documentación
<Muestre y/o detalle el diagrama de secuencia del componente en cuestión con la documentación respectiva.>
1. ## <a name="_toc149221099"></a>Componente 2
<Defina en términos comprensibles cuál es la motivación de este componente.>
1. ## <a name="_toc149221100"></a>Diagrama
<Muestre el diagrama de secuencia en cuestión.>
1. ## <a name="_toc149221101"></a>Documentación
<Muestre y/o detalle el diagrama de secuencia del componente en cuestión con la documentación respectiva.>
1. ## <a name="_toc149221102"></a>Componente N
<Defina en términos comprensibles cuál es la motivación de este componente.>
1. ## <a name="_toc149221103"></a>Diagrama
<Muestre el diagrama de secuencia en cuestión.>
1. ## <a name="_toc149221104"></a>Documentación
<Muestre y/o detalle el diagrama de secuencia del componente en cuestión con la documentación respectiva.>
1. ## <a name="_toc149221105"></a>Diagrama de colaboración
<Defina en términos comprensibles qué es diagrama de colaboración, cuál es su motivación y qué le aporta al diseño del sistema.>
1. ## <a name="_toc149221106"></a>Componente 1
<Defina en términos comprensibles cuál es la motivación de este componente.>
1. ## <a name="_toc149221107"></a>Diagrama
<Muestre el diagrama de colaboración en cuestión.>
1. ## <a name="_toc149221108"></a>Documentación
<Muestre y/o detalle el diagrama de colaboración del componente en cuestión con la documentación respectiva.>
1. ## <a name="_toc149221109"></a>Componente 2
<Defina en términos comprensibles cuál es la motivación de este componente.>
1. ## <a name="_toc149221110"></a>Diagrama
<Muestre el diagrama de colaboración en cuestión.>
1. ## <a name="_toc149221111"></a>Documentación
<Muestre y/o detalle el diagrama de colaboración del componente en cuestión con la documentación respectiva.>
1. ## <a name="_toc149221112"></a>Componente N
<Defina en términos comprensibles cuál es la motivación de este componente.>
1. ## <a name="_toc149221113"></a>Diagrama
<Muestre el diagrama de colaboración en cuestión.>
1. ## <a name="_toc149221114"></a>Documentación
<Muestre y/o detalle el diagrama de colaboración del componente en cuestión con la documentación respectiva.>
1. ## <a name="_toc149221115"></a>Vista Física/Vista de Implantación
<Defina en términos comprensibles qué es una vista física, cuál es su motivación y qué le aporta al diseño del sistema.>
1. ## <a name="_toc149221116"></a>Diagrama de despliegue
<Defina en términos comprensibles qué es diagrama de despliegue, cuál es su motivación y qué le aporta al diseño del sistema.>
1. ## <a name="_toc149221117"></a>Diagrama
<Muestre el diagrama de objetos en cuestión.>
1. ## <a name="_toc149221118"></a>Documentación
<Muestre y/o detalle el diagrama de paquetes del sistema la documentación respectiva.>






