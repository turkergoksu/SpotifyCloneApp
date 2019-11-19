package me.turkergoksu.spotifycloneapp.data

import io.reactivex.Observable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import me.turkergoksu.spotifycloneapp.data.remote.Radio
import me.turkergoksu.spotifycloneapp.data.remote.RadioServiceProvider

/**
 * Created by turkergoksu on 19-Nov-19.
 */

class RadioDataSource {
    val radioServiceProvider = RadioServiceProvider()

    fun fetchPopularRadios(): Observable<Resource<List<Radio>>> {
        return Observable.create { emitter ->
            emitter.onNext(Resource.loading())
            radioServiceProvider.getRadioService().getPopularRadios().subscribeOn(Schedulers.io())
                .subscribe(
                    { radioList ->
                        emitter.onNext(Resource.success(radioList))
                        emitter.onComplete()
                    },
                    { error ->
                        emitter.onNext(Resource.error(error.message ?: ""))
                        emitter.onComplete()
                    })
        }
    }

    fun fetchLocationRadios(): Observable<Resource<List<Radio>>> {
        return Observable.create { emitter ->
            emitter.onNext(Resource.loading())
            radioServiceProvider.getRadioService().getLocationRadios().subscribeOn(Schedulers.io())
                .subscribe(
                    { radioList ->
                        emitter.onNext(Resource.success(radioList))
                        emitter.onComplete()
                    }, { error ->
                        emitter.onNext(Resource.error(error.message ?: ""))
                        emitter.onComplete()
                    })
        }
    }
}