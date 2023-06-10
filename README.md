
# FirebaseApp

FirebaseApp is a mobile application developed on the Android platform using Firebase Authentication and Firebase Firestore. Users can sign up, log in, and create a journal where they can share their travel experiences.

## Features

-   User registration and login processes are handled using Firebase Authentication.
-   Users can create a new travel experience by adding a title, location, notes, and an image.
-   The created travel experiences are saved to Firebase Firestore and associated with the user's account.
-   Users can view their created travel experiences on the home screen.
-   Users can add images to their travel experiences and upload them to Firebase Storage.

## Installation

1.  Create a [Firebase](https://firebase.google.com/) account and create a new project.
2.  Enable Firebase Authentication and Firebase Firestore in the project settings.
3.  Download the Firebase configuration file (google-services.json) for your project and place it under the `app` module.
4.  Open the project in Android Studio and sync the project if needed.
5.  The project can be run on an Android device or an Android emulator.

## Usage

-   When the app starts, users can sign in or create a new account by entering an email and password on the home screen.
-   Upon successful login or registration, users can click the "New Experience" button on the home screen to add a travel experience.
-   On the travel experience screen, users can enter a title, location, notes, and add an image.
-   To select an image, users can click the "Add Image" button and choose an image from the gallery.
-   Users can click the "Add" button to add the travel experience. If successful, they will be redirected to the home screen and the new travel experience will be displayed in the list.
-   On the home screen, users can view their created travel experiences. Each travel experience is listed as an item with a title, location, and image.
-   To add a new travel experience, users can click the "New Experience" button.

## Contributing

If you would like to contribute, please open an issue for your proposed changes before submitting a pull request. This way, we can discuss any questions or suggestions you may have.

## License

This project is licensed under the MIT License. See the [LICENSE](https://chat.openai.com/LICENSE) file for more information.


<div align="center">
<img src="https://github.com/enesulkerr/Firebase-Traveler-App/blob/master/image/Traveler.gif" width="300">
<div align="center">
