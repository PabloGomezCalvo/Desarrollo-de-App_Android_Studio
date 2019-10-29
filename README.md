# Desarrollo-de-App_Android_Studio
Desarrollo de un juego multiplataforma para Android y Escritorio usando Java y Android Studio.
Para hacer el autoescalado deberiamos poner una clase en Interfaces.utilities que se encargue de recalcular la pos de donde quiere pintar la logica y llamar al draw de Graphics. Con eso desde Logica deberemos llamar a esta clase en vez de invocar el draw de Graphics directamente.


Con esto conseguimos que el programador de la logica tenga la opcion de usar nuestro escalado o de usar el suyo.
