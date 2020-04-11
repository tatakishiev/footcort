package configuration.kodein

import auth.kodein.AuthKodein
import company.kodein.CompanyKodein
import court.kodein.CourtKodein
import match.kodein.MatchKodein
import org.kodein.di.Kodein
import user.kodein.UserKodein

object KodeinModule {
    internal val kodein = Kodein {
        import(CourtKodein.module)
        import(UserKodein.module)
        import(AuthKodein.module)
        import(CompanyKodein.module)
        import(MatchKodein.module)
    }
}