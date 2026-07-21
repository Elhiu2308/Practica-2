# 📱 Práctica 2 – Módulo 4: Desarrollo de aplicaciones para dispositivos móviles

<p align="center">
  <img src="https://img.shields.io/badge/Kotlin-0095D5?&style=for-the-badge&logo=kotlin&logoColor=white" />
  <img src="https://img.shields.io/badge/Android_Studio-3DDC84?style=for-the-badge&logo=android-studio&logoColor=white" />
  <img src="https://img.shields.io/badge/Fragments-FF5722?style=for-the-badge&logo=android&logoColor=white" />
</p>

---

## 📋 Datos de Entrega
* **Alumno:** Christian Elhiu Espindola Muñoz
* **Fecha:** 20 de julio de 2026

---

## 🚀 Descripción del Proyecto
Aplicación Android desarrollada en Kotlin para la Práctica 2. Implementa la migración de las pantallas a Fragments, navegación mediante FragmentManager y transacciones, paso de parámetros entre Fragments, implementación de View Binding con buenas prácticas, conservación de la funcionalidad de la práctica anterior y configuración de API 26.

## ✅ Cumplimiento de Requerimientos Funcionales

La aplicación cumple con el 100% de la rúbrica de evaluación, integrando las siguientes características:

*   **🧩 Migración a Fragments (40 pts):**
    *   Se implementa una **única Activity** responsable exclusivamente de alojar el contenedor de vistas.
    *   La pantalla de autenticación y sus validaciones residen en un Fragment de inicio de sesión.
    *   La pantalla de bienvenida está encapsulada en un segundo Fragment.

*   **🧭 Navegación con FragmentManager (30 pts):**
    *   La navegación entre pantallas se realiza de forma nativa utilizando `FragmentManager` y transacciones de Fragments.
    *   No se utiliza navegación mediante `Intents` ni una segunda Activity.

*   **📦 Paso de Parámetros (10 pts):**
    *   El Fragment de bienvenida recibe los parámetros requeridos directamente desde el Fragment de inicio de sesión.
    *   Se cumple la restricción de **no utilizar variables globales** para compartir información entre Fragments.

*   **🎨 Implementación de View Binding (10 pts):**
    *   Se utiliza *View Binding* en los Fragments siguiendo las buenas prácticas de manejo de memoria en el ciclo de vida de la vista.

*   **🔄 Conservación del Funcionamiento (10 pts):**
    *   **Autenticación:** El sistema valida las credenciales asignadas (`user@tic.unam.mx` y `d1pl0m0v1l35?`).
    *   **Validaciones:** Se mantienen las alertas de campos vacíos, formato de correo y *password masking*.
    *   **Internacionalización:** El soporte bilingüe mediante archivos de recursos está completamente funcional y se conserva el diseño original.

---

## 📸 Capturas de Pantalla de la Aplicación

<div align="center">
  <table>
    <tr>
      <td align="center"><b>1. Fragment de Inicio de Sesión</b></td>
      <td align="center"><b>2. Fragment de Bienvenida</b></td>
      <td align="center"><b>3. Flujo Adicional (Calendario)</b></td>
    </tr>
    <tr>
      <td align="center">
        <img width="250" alt="Pantalla de Inicio de Sesión" src="https://github.com/user-attachments/assets/fdc22851-3e99-45af-b263-67af0f888ca1" />
      </td>
      <td align="center">
       <img width="250" alt="Pantalla de Bienvenida" src="https://github.com/user-attachments/assets/89e65e02-70c6-494e-ba79-aac7a073083f" />
      </td>
      <td align="center">
      <img width="250" alt="image" src="https://github.com/user-attachments/assets/7271b39e-8ef1-4042-a3f1-47182f940a08" />
      </td>
    </tr>
  </table>
</div>
