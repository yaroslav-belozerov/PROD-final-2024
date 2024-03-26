# LifestyleHUB

## Installation
```
git clone https://github.com/Central-University-IT-prod/mobile-yaroslav-belozerov.git
cd mobile-yaroslav-belozerov
./gradlew build
```

## Architecture
Please read the [Architecture Decision Record](https://github.com/Central-University-IT-prod/mobile-yaroslav-belozerov/blob/main/ADR.md)

## Modules
### Common
- Non-translatable UI constants in CommonConstants
- Loading Shimmer effect `.shimmer(colors: List<Color>)` with custom colors and a `ShimmerSpacer`

### Location
- `DefaultLocationTracker` that you can inject with Hilt
- `LocationTracker` interface and `LocationTrackerModule` fom injecting a custom location tracker

### Weather
> Depends on: *common*, *location*
- `data` — `remote source` and a `repository` for fetching weather with OpenWeatherMap API
- `di` — Hilt dependency injection
- `domain` — UI-ready models
- `presentation` — a Composable and a ViewModel for a single weather card

### Venues
> Depends on: *common*, *location*
- `data` package
-  - `local.room` — a Room database implementation to store venues from Foursquare API
-  - `remote.foursquare` — fetching locations from Foursquare API
-  - `repository` — managing the venues (local and remote)
-  `di` — for Hilt injection of API, Room database and a repository of venues
- `domain` — UI-ready models
- `presentation` — a Composable and a ViewModel for a single venue card

### App
> Depends on: *weather*, *venues*
- `presentation` — app's singular `MainActivity`, Screens for navigation
- - `ui` — Screens and a DI application

___
Условие задания доступно в [Notion](https://centraluniversity.notion.site/PROD-a404fd65bd6044da83fdf60859ff7733).
___
