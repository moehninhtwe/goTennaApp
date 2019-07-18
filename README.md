A simple application showing either a map with current location and other location pins, or a list with locations. It works
offline too. If there is no network available, data is retrieved from Room (if available) and display it.
It uses MVVM architecture together with Room database, databinding and dagger.
It mixes Java files with Kotlin.
Known issues - current location won't be shown up on Map for the very first launch because asking location permission 
                 and getting current location is running pararrelly with getting other locations and pinning them on map.
               - If there is no data to display, users are seeing blank page rather than an error message.
               - or other issues that I haven't found out yet.
