# Movies Forever
Test for interviews implemented in Kotlin using Coroutines for background processing, Room for local cache, Retrofit 2 for network calls and Dagger2 for DI in a MVVM architecture.

## Problem
- A user wants to see a list of movies so that they can get some idea on the movies to watch.
- A user wants to be able to click on a specific movie in the list so that they can see additional information about that movie.

## Overview
This is a movies app that allows the user to search for movies and display them on a list. The app also displays the list of the Top 20 popular movies.
When the user selects a movie from the lists, it displays more information about it.
The Top Popular movies was implemented to show the hability of using different item views in the same RecyclerView.

<img src=movies_forever.gif width="200">

## Technologies
- Implemented in Kotin
- Coroutines: the next level from RxJava, to allow responsive implementations using LiveData and Observables.
- Room: local database used as the  only source of data for the consumers (UI) which links really well with LiveData.
- Retrofit 2: network calls implementation to access the API.
- Dagger 2: for Dependency Injection through the application.
- Glide: load URL images into imageviews.

## Architecture
The project follows the MVVM architecture implementing the Repository pattern to handle the communications between Database, Network and UI.
There are 2 modules: app and core.

*App*

Module holding all the UI related implementation: Application, Activities, Views, potential Services (not implemented here), etc.

*Core*

Module holding all the app's 'backend' implementation: Database, Network calls.

## Assumptions
- Assuming that the API query searches a movie containing the text in either title or overview. This might not be right and hence it is possible that the UI doesn't show the same data as if one would query directly the source instead of querying the database. For exact result we could forget about database and do only network calls.
- To make it simple, due to time restrictions, I’ll make the details call only Database, mostly because the movie API used returns all the details in the list query. Hence, when the user is in Details, it could have only been able to get to that view if the movie was queried before for the list.

## Missing implementations and issues
- Security issue: The api key is in the resources folder. The app shouldn’t store any 'harcoded' keys on the client side. It would be better to have an authentication process with the server and the server sends a session key/token which can be securely stored in device.
- Connectivity: the UI doesn't react to connectivity states such as when the data is loading (downloading from the API) or when an error occurres (e.g. the user does't have intenernet connection). Just need to implement views when the DataResult returns the states ERROR and LOADING.
  Also a ConnectionListenerManager can be implemented with a BroadcastReceiver and accessing the ConnectivityManager.  
- Testing: VERY IMPORTANT which was not able to implement at the time. Will be implemented though for both modues (App and Core) for UI, mappers, etc. Would be implemented using JUnit, Espresso, Mockito

## Potential improvements
- We could store locally the images downloaded for better connectivity performance (in case the user doesn't have connection).
- Use glide to setup a default image when an error occurs trying to load them, and a Loading state image when the image is being loaded to the view.
- Right now the app only displays the first 20 movies. Potentially use pagination library (jetpack) to allow search for more when the user reaches the bottom of the list.

## Extras
Movie DB API used
- https://www.themoviedb.org/documentation/api
- https://developers.themoviedb.org/3/getting-started/search-and-query-for-details
