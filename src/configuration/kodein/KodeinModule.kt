package configuration.kodein

import auth.kodein.AuthKodein
import court.kodein.CourtKodein
import org.kodein.di.Kodein
import user.kodein.UserKodein

object KodeinModule {
    internal val kodein = Kodein {
        import(CourtKodein.module)
        import(UserKodein.module)
        import(AuthKodein.module)
    }
}