
# Architecture decision record
## Approach to development
### Clean architecture
#### ✅ Implemented
- Modularity: different layers' implementation can be dropped in seamlessly with nonexistent effect of the other layers
- Testability: different layers can be tested individually, decreasing CI efforts for developers
- Sustainability: it's easy to migrate only the parts of the project that require upgrade
### MVVM
#### ✅ Implemented
- Separation of concerns: separates the business-logic and the UI, minimizing risks
- Works well with CA, linking the "domain" and "presentation" layers
## Libraries
### Jetpack Compose
#### ✅ Implemented
- Ease of development: developers aren't required to switch between native Kotlin code and XML markup
- Builtins: contains many prebuilt animations, effects and functions useful for app development
- Sustainability: officially supported by Google, thus futureproof
### Retrofit
#### ✅ Implemented
- Type-safety: handles risks of unpredictable server responses
- Integration: integrates seamlessly with parsing libraries, can produce DTO's out of the box
- Standart: the most popular and well-maintained
### Moshi
#### ✅ Implemented
- Integration with Retrofit
- Efficiency: works faster than other solutions
- Smaller: more concise api and library size
### Hilt + Dagger
#### ✅ Implemented
- Developer-friendly: faster development and no boilerplate
- Better testing integration
### Kotlin Coroutines
#### ✅ Implemented
- More efficient and easy to work with than threads or callbacks
- Sustainability: officially supported by Google
- Integration with synchronous code
### Glide
#### ✅ Implemented
- Integrates well with compose
- Asynchronous loading from the web with support for placeholders
- Sustainability: most up-to-date and popular version