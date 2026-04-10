## Prompt 1

Goal: Creating a new Android project with Kotlin and XML views.

Prompt: Referring to the files in the docs folder, plan and implement step 1 in the 08_implementation_plan.md file - Create Android project with Kotlin and XML Views.

Result: The base structure for an Android app was created, with Kotlin files as placeholders for future information.

---

## Prompt 2

Goal: Creating the ImageItem model class according to the data model

Prompt: Considering the data model in the 02_data_model.md file, implement step 2 in the 08_implementation_plan.md file - Create data model classes ImageItem and BreedInformation.

Result: ImageItem and BreedInformation classes implemented, with the data model related information as parameters.

---

## Prompt 3

Goal: Implementing the API service to fetch images from The Cat API.

Prompt: Using infotmation from 07_api_usage.md, implement step 3 - the API service needed to get picture and cat breed information from The Cat API.

Result: API service and access to The Cat API implemented, with a starting limit of 30 images per fetch.

--

## Prompt 4

Goal: Creating the RecyclerView adapter for the image grid

Prompt: Referring to the featues and screens files in the docs folder, create the RecyclerView adapter for the image grid.

Result: Created the ImageAdapter class, with the necessary methods to display the images in a grid.

--

## Prompt 5

Goal: Creating XML layout for the main screen.

Prompt: Referring to the screens file in the docs folder, implement step 5 in the 08_implementation_plan.md file - Design activity_main.xml layout for the main screen. Create it with the indicated visual features.

Result: Created main menu layout with the indicated visual features, including loading and error states.

---

## Prompt 6

Goal: Connecting the view model to the main activity and the API, allowing for pictures to be displayed.

Prompts: 

Remembering and inspecting the files in /docs, implement step 6 and connect ViewModel and MainActivity.
Implement step 7, connecting the view model to the API repository and allowing it to fetch images to show in the grid.

Result: MainActivity fully connected to the view model, so it can display pictures from the API.

---

## Prompt 7

Goal: Creation of the image detail screen

Prompt: Create the image detail screen as described in 03_screens.md, including the navigation described in 05_navigation.md.

Result: If the user taps an image in the main grid, they are navigated to the image detail screen, where they can see the image and cat breed information.

---

## Prompt 8

Goal: Implementing refresh and wikipedia access functionalities.

Prompt: According to step 9 and all the files needed, implement the grid refresh and wikipedia access from a cat breed's page functionalities.

Result: A user can now refresh the main page by pressing a button, as well as open a cat breed's wikipedia page from the detail screen.

---

## Prompt 9

Goal: Implementing search by breed functionality.

Prompt: According to step 10 and all the files needed, implement the "search by breed of the cats in the images" functionality.

Result: Users can now access an interactive search bar in the main screen, allowing them to search by breed of the cats in the images.

---

## Prompt 10

Goal: Set an app icon and name in toolbar

Prompt: Set the app icon using the resource @drawable/app_icon which I have placed in the drawable folder, first generating a Vector Asset based on it. Use it as an icon as well as show it in the app toolbar, before the app name.

Result: App icon set and shown in the toolbar before the app name.

--

## Prompt 11

Goal: Implementing a dark mode for the app

Prompt: Create a dark theme for the app for dark mode users, where the background is black and the text is white. The toolbar and buttons are a darker shade of its current color.

Result: Created more color resources and a dark theme for the app.