package org.addepar.investmenttracker.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import org.addepar.investmenttracker.core.data.api.InvestmentsApi
import org.addepar.investmenttracker.core.data.sources.fake.FakeInvestmentsSource
import org.addepar.investmenttracker.feature.details.data.InvestmentDetailsRepo
import org.addepar.investmenttracker.feature.details.data.InvestmentDetailsRepoImp
import org.addepar.investmenttracker.feature.home.data.HomeRepo
import org.addepar.investmenttracker.feature.home.data.HomeRepoImp

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepositoryModule {

    @Binds
    @FakeInvestmentApi
    abstract fun bindInvestmentsApi(fakeInvestmentsApi: FakeInvestmentsSource): InvestmentsApi

    @Binds
    abstract fun bindHomeRepo(homeRepo: HomeRepoImp): HomeRepo

    @Binds
    abstract fun bindInvestmentDetailsRepo(investmentDetailsRepo: InvestmentDetailsRepoImp): InvestmentDetailsRepo
}

