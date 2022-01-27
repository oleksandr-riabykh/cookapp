# Cook Test App
## The app was written to show the people how I'm writing the code

All provided APIs are used in the offer. The main functionality consists of:
- collections
- recipes
- profile

## PreSetup

>Please make sure you are using Java 11 Gradle jdk! Go to Android Studio preferences -> Build, Execution, Deployment -> Build tools -> Gradle JDK -> select 11 version
>I would recommend using devices with compile SDK versions compileSdk 31 and more, since I add splash screen in the app using themes(https://developer.android.com/guide/topics/ui/splash-screen), looks good :). I didn't use animation in the splash.

## Implemented

I've used such tech stack in the project:

- Android ViewModel
- Android Jetpack's Navigation component
- DataBinding/ViewBinding, DataBinding is present in the adapters
- Hilt for dependency injection
- LiveData
- Coroutines
- Room DB Framework just for example

## Todo

The things I would like to improve in the future

- Load cached collection by id
- Make abstraction for some classes, for example, to move generic method toUIModel on top
- Add CoordinatorLayout to collection details view, make good animation of scrolling lists
- Add more DataBinding
- Update items for recipes and collection, move title to bottom part of item, and add number of recipes in the collection
- Handle null/empty list of urls properly, add view pager for list of images
- Display message when user gets empty collection/recipes
- Think about image scaling in order to imroove UI/UX
- Enable night theme, develop color schema
- UI for landscape orientation
- Handle dates in the network layer
- Improve tests(make real ones) and increase test coverage

## License

MIT