package com.example.comunicafacilapp

// centraliza la coleccion de consejos y las operaciones asociadas
class ConsejoRepository {

    companion object {

        val consejos = listOf(
            // Categoria: Comunicacion
            Consejo(
                categoria = "Comunicación",
                titulo = "Busca contacto visual",
                descripcion = "Procura mantener contacto visual con la persona con quien te comunicas para facilitar la comprensión.",
                emoji = "👀"
            ),
            Consejo(
                categoria = "Comunicación",
                titulo = "Utiliza mensajes escritos",
                descripcion = "Apóyate en mensajes escritos cuando necesites complementar o aclarar una conversación.",
                emoji = "💬"
            ),
            Consejo(
                categoria = "Comunicación",
                titulo = "Prefiere lugares iluminados",
                descripcion = "Una buena iluminación puede facilitar la comunicación visual y la lectura de expresiones o labios.",
                emoji = "💡"
            ),

            // Categoria: Emergencias
            Consejo(
                categoria = "Emergencias",
                titulo = "Mantén tus datos disponibles",
                descripcion = "Ten información personal y de contacto importante disponible para mostrarla rápidamente en una emergencia.",
                emoji = "🪪"
            ),
            Consejo(
                categoria = "Emergencias",
                titulo = "Define un contacto de emergencia",
                descripcion = "Mantén identificado un contacto de confianza al que puedan recurrir otras personas si necesitas ayuda.",
                emoji = "📞"
            ),
            Consejo(
                categoria = "Emergencias",
                titulo = "Comunica cómo ayudarte",
                descripcion = "Si es necesario, utiliza texto para indicar de forma clara cómo prefieres comunicarte durante una emergencia.",
                emoji = "🚨"
            ),

            // Categoria: Accesibilidad
            Consejo(
                categoria = "Accesibilidad",
                titulo = "Revisa las opciones de accesibilidad",
                descripcion = "Explora las funciones de accesibilidad disponibles en tu dispositivo y utiliza las que faciliten su uso cotidiano.",
                emoji = "♿"
            ),
            Consejo(
                categoria = "Accesibilidad",
                titulo = "Utiliza alertas visuales",
                descripcion = "Cuando estén disponibles, utiliza alertas visuales o vibración como apoyo a las notificaciones sonoras.",
                emoji = "📳"
            ),
            Consejo(
                categoria = "Accesibilidad",
                titulo = "Personaliza tu dispositivo",
                descripcion = "Configura textos, notificaciones y otros elementos visuales de una forma cómoda y fácil de identificar.",
                emoji = "⚙️"
            )
        )

        // obtiene los consejos correspondientes a la categoria seleccionada
        fun obtenerConsejosPorCategoria(categoria: String): List<Consejo> {
            return consejos.filter {
                it.categoria == categoria
            }
        }
    }
}