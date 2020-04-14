package exception

import java.lang.Exception
import kotlin.reflect.KClass

open class EntityNotFoundException(val id: Long, entity: KClass<*>) :
    Exception("Entity ${entity.simpleName} with id=$id not found in database")