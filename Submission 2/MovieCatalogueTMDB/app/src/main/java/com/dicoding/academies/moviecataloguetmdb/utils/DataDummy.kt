package com.dicoding.academies.moviecataloguetmdb.utils

import com.dicoding.academies.moviecataloguetmdb.data.source.local.entity.DetailMovieEntity
import com.dicoding.academies.moviecataloguetmdb.data.source.local.entity.DetailTVShowEntity
import com.dicoding.academies.moviecataloguetmdb.data.source.local.entity.MovieEntity
import com.dicoding.academies.moviecataloguetmdb.data.source.local.entity.TVShowEntity
import com.dicoding.academies.moviecataloguetmdb.data.source.remote.response.*

object DataDummy {

    fun generateDummyMovies(): ArrayList<MovieEntity> {
        val movies = ArrayList<MovieEntity>()
        movies.add(MovieEntity(581389,
            "/y2Yp7KC2FJSsdlRM5qkkIwQGCqU.jpg",
            "Space Sweepers",
            "When the crew of a space junk collector ship called The Victory discovers a humanoid robot named Dorothy that's known to be a weapon of mass destruction, they get involved in a risky business deal which puts their lives at stake.",
            "2021-02-05"))
        movies.add(MovieEntity(464052,
            "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
            "Wonder Woman 1984",
            "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
            "2020-12-16"))
        movies.add(MovieEntity(587996,
            "/dWSnsAGTfc8U27bWsy2RfwZs0Bs.jpg",
            "Below Zero",
            "When a prisoner transfer van is attacked, the cop in charge must fight those inside and outside while dealing with a silent foe: the icy temperatures.",
            "2021-01-29"))
        return movies
    }

    fun generateDummyTVShows(): ArrayList<TVShowEntity> {
        val tvShows = ArrayList<TVShowEntity>()
        tvShows.add(TVShowEntity(85271,
            "/atBOisJ8YNY31w5ZSy7y7uVecXN.jpg",
            "WandaVision",
            "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
            "2021-01-15"))
        tvShows.add(TVShowEntity(69050,
            "/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg",
            "Riverdale",
            "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
            "2017-01-25"))
        tvShows.add(TVShowEntity(71712,
            "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
            "The Good Doctor",
            "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
            "2017-09-25"))
        return tvShows
    }

    fun generateDummyDetailMovie(movieId: Int): ArrayList<DetailMovieEntity> {
        val detailMovie = ArrayList<DetailMovieEntity>()
        detailMovie.add(DetailMovieEntity(movieId,
            "/y2Yp7KC2FJSsdlRM5qkkIwQGCqU.jpg",
            "Space Sweepers",
            "2021-02-05",
            "Drama, Fantasy, Science Fiction",
            2149.307,
            73,
            "2092, the space sweep begins!",
            "When the crew of a space junk collector ship called The Victory discovers a humanoid robot named Dorothy that's known to be a weapon of mass destruction, they get involved in a risky business deal which puts their lives at stake.",
            "Bidangil Pictures (KR), Merry Christmas (KR)"))
        detailMovie.add(DetailMovieEntity(movieId,
            "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
            "Wonder Woman 1984",
            "2020-12-16",
            "Fantasy, Action, Adventure",
            2039.461,
            69,
            "A new era of wonder begins.",
            "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
            "DC Entertainment (US), Warner Bros. Pictures (US), The Stone Quarry (US), DC Films (US), Atlas Entertainment (US), DC Comics (US)"))
        detailMovie.add(DetailMovieEntity(movieId,
            "/dWSnsAGTfc8U27bWsy2RfwZs0Bs.jpg",
            "Below Zero",
            "2021-01-29",
            "Action, Crime, Thriller",
            1504.943,
            64,
            "Escape is not the exit.",
            "When a prisoner transfer van is attacked, the cop in charge must fight those inside and outside while dealing with a silent foe: the icy temperatures.",
            "Morena Films (ES), Amorós Producciones, ICEC (ES), TVE (ES)"))
        return detailMovie
    }

    fun generateDummyDetailTVShow(tvId: Int): ArrayList<DetailTVShowEntity> {
        val detailTvShow = ArrayList<DetailTVShowEntity>()
        detailTvShow.add(DetailTVShowEntity(tvId,
            "/atBOisJ8YNY31w5ZSy7y7uVecXN.jpg",
            "WandaVision",
            "2021-01-15",
            "Sci-Fi & Fantasy, Mystery, Drama",
            3170.54,
            84,
            "Experience a new vision of reality.",
            "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
            "Marvel Studios (US)"))
        detailTvShow.add(DetailTVShowEntity(tvId,
            "/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg",
            "Riverdale",
            "2017-01-26",
            "Mystery, Drama, Crime",
            1410.968,
            86,
            "Small town. Big secrets.",
            "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
            "Warner Bros. Television (US), Berlanti Productions (US)"))
        detailTvShow.add(DetailTVShowEntity(tvId,
            "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
            "The Good Doctor",
            "2017-09-25",
            "Drama",
            825.408,
            86,
            "His mind is a mystery, his methods are a miracle.",
            "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
            "ABC Studios (US), 3AD (US), Sony Pictures Television (US)"))
        return detailTvShow
    }
    // remote
    fun generateRemoteDummyMoviesItem(): ArrayList<MoviesItemResponse> {
        val movies = ArrayList<MoviesItemResponse>()
        movies.add(MoviesItemResponse("When the crew of a space junk collector ship called The Victory discovers a humanoid robot named Dorothy that's known to be a weapon of mass destruction, they get involved in a risky business deal which puts their lives at stake.",
            "Space Sweepers",
            "/y2Yp7KC2FJSsdlRM5qkkIwQGCqU.jpg",
            "2021-02-05",
            581389))
        return movies
    }

    fun generateRemoteDummyTVShowsItem(): ArrayList<TVShowsItemResponse> {
        val tvShows = ArrayList<TVShowsItemResponse>()
        tvShows.add(TVShowsItemResponse("2021-01-15",
            "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
            "/atBOisJ8YNY31w5ZSy7y7uVecXN.jpg",
            "WandaVision",
            85271))
        return tvShows
    }

    fun generateRemoteDummyDetailMovie(movieId: Int): ArrayList<DetailMovieResponse> {
        val detailMovie = ArrayList<DetailMovieResponse>()
        detailMovie.add(DetailMovieResponse("Space Sweepers",
            listOf(
                MovieGenresItemResponse("Drama,"),
                MovieGenresItemResponse("Fantasy"),
                MovieGenresItemResponse("Science Fiction")),
                2149.307,
            movieId,
            "When the crew of a space junk collector ship called The Victory discovers a humanoid robot named Dorothy that's known to be a weapon of mass destruction, they get involved in a risky business deal which puts their lives at stake.",
            "/y2Yp7KC2FJSsdlRM5qkkIwQGCqU.jpg",
            listOf(
                MovieProductionCompaniesItemResponse("Bidangil Pictures", "KR"),
                MovieProductionCompaniesItemResponse("Merry Christmas", "KR")),
            "2021-02-05",
            7.3,
            "2092, the space sweep begins!"))
        return detailMovie
    }

    fun generateRemoteDummyDetailTVShow(tvId: Int): ArrayList<DetailTVShowResponse> {
        val detailTvShow = ArrayList<DetailTVShowResponse>()
        detailTvShow.add(DetailTVShowResponse(
            listOf(
                TVShowGenresItemResponse("Sci-Fi & Fantasy"),
                TVShowGenresItemResponse("Mystery"),
                TVShowGenresItemResponse("Drama")),
            3170.54,
            tvId,
            "2021-01-15",
            "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
            "/atBOisJ8YNY31w5ZSy7y7uVecXN.jpg",
            listOf(TVShowProductionCompaniesItemResponse("Marvel Studios", "US")),
            8.4,
            "WandaVision",
            "Experience a new vision of reality."))
        return detailTvShow
    }
}