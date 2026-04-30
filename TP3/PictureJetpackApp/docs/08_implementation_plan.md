# Implementation Plan

Step 1
Create Android project with Kotlin and XML Views.

Step 2
Create data model classes ImageItem and BreedInformation.

Step 3
Implement API service to fetch images.

Step 4
Create RecyclerView for grid.

Step 5
Design activity_main.xml layout.

Step 6
Connect ViewModel to API repository.

Step 7
Display images in grid, connecting ViewModel to MainActivity.

Step 8
Create image detail screen layout and navigation.

Step 9
Add refresh and wikipedia accesss button functionality

Step 10
Add search by breed functionality

Step 11
Create and set an app icon

Step 12
Implement dark mode

Step 13
Implement progress bar for loading images

Step 14
Implement favorite items functionality

Step 15 (MIP 3)
Separate the application into modules: :core, which has the data models and API access, and :app-xml, which has the XML Views application related resources.

Step 16
Create the :app-compose module, and rewrite the XML Views application related resources in the :app-xml module to Jetpack Compose.

Step 17
Only in the :app-compose module, add a pull-to-refresh functionality for the image grid, which replaces the refresh button.

Step 18 
Only in the :app-compose module, add an animation between main and detail screens, which enlarges the selected image until the desired final size.
