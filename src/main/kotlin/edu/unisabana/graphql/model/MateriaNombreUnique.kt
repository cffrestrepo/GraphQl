package edu.unisabana.graphql.model

import edu.unisabana.graphql.service.MateriaService
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass
import org.springframework.web.servlet.HandlerMapping


/**
 * Validate that the nombre value isn't taken yet.
 */
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER,
        AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [MateriaNombreUniqueValidator::class])
annotation class MateriaNombreUnique(
    val message: String = "{Exists.materia.nombre}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)


class MateriaNombreUniqueValidator(
    private val materiaService: MateriaService,
    private val request: HttpServletRequest
) : ConstraintValidator<MateriaNombreUnique, String> {

    override fun isValid(`value`: String?, cvContext: ConstraintValidatorContext): Boolean {
        if (value == null) {
            // no value present
            return true
        }
        @Suppress("unchecked_cast") val pathVariables =
                (request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE) as
                Map<String, String>)
        val currentId = pathVariables.get("id")
        if (currentId != null && value.equals(materiaService.get(currentId.toLong()).nombre,
                ignoreCase = true)) {
            // value hasn't changed
            return true
        }
        return !materiaService.nombreExists(value)
    }

}
