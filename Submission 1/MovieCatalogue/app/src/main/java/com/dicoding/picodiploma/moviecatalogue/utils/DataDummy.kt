package com.dicoding.picodiploma.moviecatalogue.utils

import com.dicoding.picodiploma.moviecatalogue.R
import com.dicoding.picodiploma.moviecatalogue.data.MovieEntity
import com.dicoding.picodiploma.moviecatalogue.data.TVShowEntity

object DataDummy {

    private val dataMoviePoster = intArrayOf(R.drawable.poster_a_start_is_born,
        R.drawable.poster_alita,
        R.drawable.poster_aquaman,
        R.drawable.poster_cold_persuit,
        R.drawable.poster_creed,
        R.drawable.poster_crimes,
        R.drawable.poster_glass,
        R.drawable.poster_how_to_train,
        R.drawable.poster_infinity_war,
        R.drawable.poster_mortal_engines)

    private val dataMovieName = arrayOf("A Star Is Born",
        "Alita: Battle Angel",
        "Aquaman",
        "Cold Pursuit",
        "Creed II",
        "Fantastic Beasts: The Crimes of Grindelwald",
        "Glass",
        "How to Train Your Dragon: The Hidden World",
        "Avengers: Infinity War",
        "Mortal Engines")

    private val dataMovieYear = arrayOf(2018, 2019, 2018, 2019, 2018, 2018, 2019, 2019, 2018, 2018)
    private val dataMovieRelease = arrayOf("10/05/2018 (US)",
        "10/05/2018 (US)",
        "12/21/2018 (US)",
        "02/08/2019 (US)",
        "11/21/2018 (US)",
        "11/16/2018 (US)",
        "01/18/2019 (US)",
        "02/22/2019 (US)",
        "04/27/2018 (US)",
        "12/14/2018 (US)")

    private val dataMovieGenre = arrayOf("Drama, Romance, Music",
        "Action, Science Fiction, Adventure",
        "Action, Adventure, Fantasy",
        "Action, Crime, Thriller",
        "Drama",
        "Adventure, Fantasy, Drama",
        "Thriller, Drama, Science Fiction",
        "Animation, Family, Adventure",
        "Adventure, Action, Science Fiction",
        "Adventure, Science Fiction")

    private val dataMovieDuration = arrayOf("2h 16m", "2h 2m", "2h 24m", "1h 59m",
        "2h 10m", "2h 14m", "2h 9m", "1h 44m", "2h 29m", "2h 9m")
    private val dataMovieScore = arrayOf(75, 71, 69, 56, 69, 69, 66, 78, 83, 61)
    private val dataMovieTagLine = arrayOf("-",
        "An angel falls. A warrior rises.",
        "Home Is Calling", "Meet Nels Coxman. Citizen of the Year.",
        "There\'s More to Lose than a Title",
        "Fate of One. Future of All.",
        "You Cannot Contain What You Are",
        "The friendship of a lifetime",
        "An entire universe. Once and for all.",
        "Some scars never heal")

    private val dataMovieOverview = arrayOf("Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally\'s career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
        "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
        "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm\'s half-human, half-Atlantean brother and true heir to the throne.",
        "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son\'s murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking\'s associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
        "Between personal obligations and training for his next big fight against an opponent with ties to his family\'s past, Adonis Creed is up against the challenge of his life.",
        "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
        "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
        "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
        "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
        "Many thousands of years in the future, Earth’s cities roam the globe on huge wheels, devouring each other in a struggle for ever diminishing resources. On one of these massive traction cities, the old London, Tom Natsworthy has an unexpected encounter with a mysterious young woman from the wastelands who will change the course of his life forever.")

    private val dataMoviePerson = arrayOf("Bradley Cooper (Director, Screenplay), Eric Roth (Screenplay), Will Fetters (Screenplay), Robert Carson (Story), William A. Wellman (Story)",
        "Robert Rodriguez (Director), Laeta Kalogridis (Screenplay), James Cameron (Screenplay)",
        "James Wan (Director, Story), Will Beall (Screenplay, Story), Mort Weisinger (Characters), Paul Norris (Characters), David Leslie Johnson-McGoldrick (Screenplay), Geoff Johns (Story)",
        "Hans Petter Moland (Director), Frank Baldwin (Screenplay)",
        "Sylvester Stallone (Characters, Screenplay), Steven Caple Jr. (Director), Juel Taylor (Screenplay), Sascha Penn (Story), Cheo Hodari Coker (Story)",
        "David Yates (Director), J.K. Rowling (Screenplay)",
        "M. Night Shyamalan (Director, Screenplay)",
        "Dean DeBlois (Director, Screenplay, Story), Cressida Cowell (Novel)",
        "Anthony Russo (Director), Joe Russo (Director), Christopher Markus (Screenplay), Stephen McFeely (Screenplay)",
        "Christian Rivers (Director), Philip Reeve (Novel), Philippa Boyens (Screenplay), Peter Jackson (Screenplay), Fran Walsh (Screenplay)")

    private val dataTVShowPoster = intArrayOf(R.drawable.poster_arrow,
        R.drawable.poster_doom_patrol,
        R.drawable.poster_dragon_ball,
        R.drawable.poster_fairytail,
        R.drawable.poster_family_guy,
        R.drawable.poster_flash,
        R.drawable.poster_gotham,
        R.drawable.poster_grey_anatomy,
        R.drawable.poster_hanna,
        R.drawable.poster_naruto_shipudden)

    private val dataTVShowName = arrayOf("Arrow",
        "Doom Patrol",
        "Dragon Ball",
        "Fairy Tail",
        "Family Guy",
        "The Flash",
        "Gotham",
        "Grey\'s Anatomy",
        "Hanna",
        "Naruto Shippūden")

    private val dataTVShowYear = arrayOf(2012, 2019, 1986, 2009, 1999, 2014, 2014, 2005, 2019, 2007)
    private val dataTVShowGenre = arrayOf("Crime, Drama, Mystery, Action &amp; Adventure",
        "Sci-Fi & Fantasy, Action & Adventure, Comedy",
        "Comedy, Sci-Fi & Fantasy, Animation, Action & Adventure",
        "Action & Adventure, Animation, Comedy, Sci-Fi & Fantasy",
        "Animation, Comedy",
        "Drama, Sci-Fi & Fantasy",
        "Drama, Fantasy, Crime",
        "Drama",
        "Action & Adventure, Drama",
        "Animation, Action & Adventure, Sci-Fi & Fantasy")

    private val dataTVShowDuration = arrayOf("42m", "49m", "25m", "25m",
        "22m", "44m", "43m", "43m", "50m", "25m")
    private val dataTVShowScore = arrayOf(66, 76, 81, 77, 69, 76, 75, 82, 74, 86)
    private val dataTVShowTagLine = arrayOf("Heroes fall. Legends rise.",
        "-",
        "-",
        "-",
        "Parental Discretion Advised, that\'s how you know it\'s good",
        "The fastest man alive.",
        "Before Batman, there was Gotham.",
        "The life you save may be your own.",
        "-",
        "-")

    private val dataTVShowOverview = arrayOf("Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
        "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
        "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the mystical Dragon Balls brought her to Goku\'s home. Together, they set off to find all seven and to grant her wish.",
        "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn\'t just any ordinary kid, he\'s a member of one of the world\'s most infamous mage guilds: Fairy Tail.",
        "Sick, twisted, politically incorrect and Freakin\' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he\'s not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
        "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only human who was created in the wake of the accelerator explosion — and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won\'t be long before the world learns what Barry Allen has become…The Flash.",
        "Everyone knows the name Commissioner Gordon. He is one of the crime world\'s greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon\'s story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world\'s most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
        "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
        "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
        "Naruto Shippuuden is the continuation of the original animated TV series Naruto. The story revolves around an older and slightly more matured Uzumaki Naruto and his quest to save his friend Uchiha Sasuke from the grips of the snake-like Shinobi, Orochimaru. After 2 and a half years Naruto finally returns to his village of Konoha, and sets about putting his ambitions to work, though it will not be easy, as He has amassed a few (more dangerous) enemies, in the likes of the shinobi organization; Akatsuki.")

    private val dataTVShowPerson = arrayOf("Greg Berlanti (Creator), Marc Guggenheim (Creator), Andrew Kreisberg (Creator)",
        "Jeremy Carver (Creator)",
        "Akira Toriyama (Creator)",
        "-",
        "Seth MacFarlane (Creator)",
        "Greg Berlanti (Creator), Geoff Johns (Creator), Andrew Kreisberg (Creator)",
        "Bruno Heller (Creator)",
        "Shonda Rhimes (Creator)",
        "David Farr (Creator)",
        "-")

    fun generateDummyMoviesItem(): ArrayList<MovieEntity> {
        val movieEntities = ArrayList<MovieEntity>()
        for (position in dataMovieName.indices) {
            val movieEntity = MovieEntity(
                dataMoviePoster[position],
                dataMovieName[position],
                dataMovieYear[position],
                dataMovieRelease[position],
                dataMovieGenre[position],
                dataMovieDuration[position],
                dataMovieScore[position],
                dataMovieTagLine[position],
                dataMovieOverview[position],
                dataMoviePerson[position]
            )
            movieEntities.add(movieEntity)
        }
        return movieEntities
    }

    fun generateDummyTVShowsItem(): ArrayList<TVShowEntity> {
        val tvShowEntities = ArrayList<TVShowEntity>()
        for (position in dataTVShowName.indices) {
            val tvShowEntity = TVShowEntity(
                dataTVShowPoster[position],
                dataTVShowName[position],
                dataTVShowYear[position],
                dataTVShowGenre[position],
                dataTVShowDuration[position],
                dataTVShowScore[position],
                dataTVShowTagLine[position],
                dataTVShowOverview[position],
                dataTVShowPerson[position]
            )
            tvShowEntities.add(tvShowEntity)
        }
        return tvShowEntities
    }
}