# Cook Test App
## Code Example

All provided APIs are used in the offer. The main functionality consists of:
- Collections. User can see the list of recipes collection, navigate through and open recipe in the collection.
- Recipes. User can see the list of recipes and open recipe.
- Profile, just an example.

## PreSetup

>Please make sure you are using Java 11 Gradle jdk! Go to Android Studio preferences -> Build, Execution, Deployment -> Build tools -> Gradle JDK -> select 11 version

## Implemented

Technology stack:

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
- Improve Sync caching logic for collections and add it for recipes. Add offline mode
- Error handling
- Add Save recipes locally
- Image shared transitions
- Was thinking to migrate the app to compose
- Add data binding to Recipe detail

## License

MIT