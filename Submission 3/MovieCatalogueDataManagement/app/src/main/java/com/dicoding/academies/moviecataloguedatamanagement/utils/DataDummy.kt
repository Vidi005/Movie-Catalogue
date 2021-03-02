package com.dicoding.academies.moviecataloguedatamanagement.utils

import com.dicoding.academies.moviecataloguedatamanagement.data.source.local.entity.MovieEntity
import com.dicoding.academies.moviecataloguedatamanagement.data.source.local.entity.TVShowEntity
import com.dicoding.academies.moviecataloguedatamanagement.data.source.remote.response.*

object DataDummy {

    fun generateDummyMovies(): ArrayList<MovieEntity> {
        val movies = ArrayList<MovieEntity>()
        movies.add(MovieEntity(458576,
            "/uwjaCH7PiWrkz7oWJ4fcL3xGrb0.jpg",
            "Monster Hunter",
            "2020-12-03",
            "",
            3079.082,
            0,
            "",
            "A portal transports Cpt. Artemis and an elite unit of soldiers to a strange world where powerful monsters rule with deadly ferocity. Faced with relentless danger, the team encounters a mysterious hunter who may be their only hope to find a way home.",
            "",
            false))
        movies.add(MovieEntity(649087,
            "/xZ2KER2gOHbuHP2GJoODuXDSZCb.jpg",
            "Red Dot",
            "2021-02-11",
            "",
            2621.134,
            0,
            "",
            "On a hiking trip to rekindle their marriage, a couple find themselves fleeing for their lives in the unforgiving wilderness from an unknown shooter.",
            "",
            false))
        movies.add(MovieEntity(581389,
            "/bmemsraCG1kIthY74NjDnnLRT2Q.jpg",
            "Space Sweepers",
            "2021-02-05",
            "",
            2121.012,
            0,
            "",
            "When the crew of a space junk collector ship called The Victory discovers a humanoid robot named Dorothy that's known to be a weapon of mass destruction, they get involved in a risky business deal which puts their lives at stake.",
            "",
            false))
        return movies
    }

    fun generateDummyTVShows(): ArrayList<TVShowEntity> {
        val tvShows = ArrayList<TVShowEntity>()
        tvShows.add(TVShowEntity(85271,
            "/atBOisJ8YNY31w5ZSy7y7uVecXN.jpg",
            "WandaVision",
            "2021-01-15",
            "",
            3605.076,
            0,
            "",
            "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
            "",
            false))
        tvShows.add(TVShowEntity(69050,
            "/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg",
            "Riverdale",
            "2017-01-25",
            "",
            1280.038,
            0,
            "",
            "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
            "",
            false))
        tvShows.add(TVShowEntity(1402,
            "/rqeYMLryjcawh2JeRpCVUDXYM5b.jpg",
            "The Walking Dead",
            "2010-10-31",
            "",
            1176.389,
            0,
            "",
            "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
            "",
            false))
        return tvShows
    }

    fun generateDummyDetailMovie(movieId: Int): ArrayList<MovieEntity> {
        val detailMovie = ArrayList<MovieEntity>()
        detailMovie.add(MovieEntity(movieId,
            "/uwjaCH7PiWrkz7oWJ4fcL3xGrb0.jpg",
            "Monster Hunter",
            "2020-12-03",
            "Fantasy, Action, Adventure",
            3910.824,
            73,
            "Behind our world, there is another.",
            "A portal transports Cpt. Artemis and an elite unit of soldiers to a strange world where powerful monsters rule with deadly ferocity. Faced with relentless danger, the team encounters a mysterious hunter who may be their only hope to find a way home.",
            "CAPCOM (JP), Constantin Film (DE), Impact Pictures (CA), Tencent Pictures (CN), Toho Company, Ltd. (JP), Screen Gems (US), Sony Pictures (US)"))
        detailMovie.add(MovieEntity(movieId,
            "/xZ2KER2gOHbuHP2GJoODuXDSZCb.jpg",
            "Ret Dot",
            "2021-02-11",
            "Drama, Thriller",
            2621.134,
            65,
            "-",
            "On a hiking trip to rekindle their marriage, a couple find themselves fleeing for their lives in the unforgiving wilderness from an unknown shooter.",
            "Film i Dalarna (SE), SF Studio (SE)"))
        detailMovie.add(MovieEntity(movieId,
            "/bmemsraCG1kIthY74NjDnnLRT2Q.jpg",
            "Space Sweepers",
            "2021-02-05",
            "Drama, Fantasy, Science Fiction",
            2121.012,
            73,
            "2092, the space sweep begins!",
            "When the crew of a space junk collector ship called The Victory discovers a humanoid robot named Dorothy that's known to be a weapon of mass destruction, they get involved in a risky business deal which puts their lives at stake.",
            "Bidangil Pictures (KR), Merry Christmas (KR)"))
        return detailMovie
    }

    fun generateDummyDetailTVShow(tvId: Int): ArrayList<TVShowEntity> {
        val detailTvShow = ArrayList<TVShowEntity>()
        detailTvShow.add(TVShowEntity(tvId,
            "/atBOisJ8YNY31w5ZSy7y7uVecXN.jpg",
            "WandaVision",
            "2021-01-15",
            "Sci-Fi & Fantasy, Mystery, Drama",
            3605.076,
            85,
            "Experience a new vision of reality.",
            "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
            "Marvel Studios (US)"))
        detailTvShow.add(TVShowEntity(tvId,
            "/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg",
            "Riverdale",
            "2017-01-26",
            "Mystery, Drama, Crime",
            1280.038,
            86,
            "Small town. Big secrets.",
            "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
            "Warner Bros. Television (US), Berlanti Productions (US)"))
        detailTvShow.add(TVShowEntity(tvId,
            "/rqeYMLryjcawh2JeRpCVUDXYM5b.jpg",
            "The Walking Dead",
            "2010-10-31",
            "Action & Adventure, Drama, Sci-Fi & Fantasy",
            1176.389,
            80,
            "Fight the dead. Fear the living.",
            "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
            "AMC Networks (US), Circle of Confusion (US), Valhalla Motion Pictures (US), Darkwoods Productions (US)"))
        return detailTvShow
    }
    // remote
    fun generateRemoteDummyMoviesItem(): ArrayList<MoviesItemResponse> {
        val movies = ArrayList<MoviesItemResponse>()
        movies.add(MoviesItemResponse("A portal transports Cpt. Artemis and an elite unit of soldiers to a strange world where powerful monsters rule with deadly ferocity. Faced with relentless danger, the team encounters a mysterious hunter who may be their only hope to find a way home.",
            "Monster Hunter",
            "/uwjaCH7PiWrkz7oWJ4fcL3xGrb0.jpg",
            "2020-12-03",
            458576,
            3079.082))
        movies.add(MoviesItemResponse("On a hiking trip to rekindle their marriage, a couple find themselves fleeing for their lives in the unforgiving wilderness from an unknown shooter.",
            "Red Dot",
            "/xZ2KER2gOHbuHP2GJoODuXDSZCb.jpg",
            "2021-02-11",
            649087,
            2621.134))
        movies.add(MoviesItemResponse("When the crew of a space junk collector ship called The Victory discovers a humanoid robot named Dorothy that's known to be a weapon of mass destruction, they get involved in a risky business deal which puts their lives at stake.",
            "Space Sweepers",
            "/bmemsraCG1kIthY74NjDnnLRT2Q.jpg",
            "2021-02-05",
            581389,
            2121.012))
        return movies
    }

    fun generateRemoteDummyTVShowsItem(): ArrayList<TVShowsItemResponse> {
        val tvShows = ArrayList<TVShowsItemResponse>()
        tvShows.add(TVShowsItemResponse("2021-01-15",
            "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
            "/atBOisJ8YNY31w5ZSy7y7uVecXN.jpg",
            "WandaVision",
            85271,
            3605.076))
        tvShows.add(TVShowsItemResponse("2017-01-25",
            "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
            "/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg",
            "Riverdale",
            69050,
            1280.038))
        tvShows.add(TVShowsItemResponse("2021-02-19",
            "In an inaccessible place between the mountains and isolated from the world, a school is located next to an old monastery. The students are rebellious and problematic young people who live under the strict and severe discipline imposed by the center to reintegrate them into society. The surrounding forest is home to ancient legends, threats that are still valid and that will immerse them in terrifying adventures.",
            "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
            "The Boarding School: Las Cumbres",
            97513,
            966.99))
        return tvShows
    }

    fun generateRemoteDummyDetailMovie(movieId: Int): ArrayList<DetailMovieResponse> {
        val detailMovie = ArrayList<DetailMovieResponse>()
        detailMovie.add(DetailMovieResponse("Monster Hunter",
            listOf(
                MovieGenresItemResponse("Fantasy"),
                MovieGenresItemResponse("Action"),
                MovieGenresItemResponse("Adventure")),
            3910.824,
            movieId,
            "A portal transports Cpt. Artemis and an elite unit of soldiers to a strange world where powerful monsters rule with deadly ferocity. Faced with relentless danger, the team encounters a mysterious hunter who may be their only hope to find a way home.",
            "/uwjaCH7PiWrkz7oWJ4fcL3xGrb0.jpg",
            listOf(
                MovieProductionCompaniesItemResponse("CAPCOM", "JP"),
                MovieProductionCompaniesItemResponse("Constantin Film", "DE"),
                MovieProductionCompaniesItemResponse("Impact Pictures", "CA"),
                MovieProductionCompaniesItemResponse("Tencent Pictures", "CN"),
                MovieProductionCompaniesItemResponse("Toho Company, Ltd.", "JP"),
                MovieProductionCompaniesItemResponse("Screen Gems", "US"),
                MovieProductionCompaniesItemResponse("Sony Pictures", "US")),
            "2020-12-03",
            7.3,
            "Behind our world, there is another."))
        return detailMovie
    }

    fun generateRemoteDummyDetailTVShow(tvId: Int): ArrayList<DetailTVShowResponse> {
        val detailTvShow = ArrayList<DetailTVShowResponse>()
        detailTvShow.add(DetailTVShowResponse(
            listOf(
                TVShowGenresItemResponse("Sci-Fi & Fantasy"),
                TVShowGenresItemResponse("Mystery"),
                TVShowGenresItemResponse("Drama")),
            3605.076,
            tvId,
            "2021-01-15",
            "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
            "/atBOisJ8YNY31w5ZSy7y7uVecXN.jpg",
            listOf(TVShowProductionCompaniesItemResponse("Marvel Studios", "US")),
            8.5,
            "WandaVision",
            "Experience a new vision of reality."))
        return detailTvShow
    }
}