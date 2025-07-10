# Интеграция Compose Multiplatform с React Native

## Обзор

Этот проект демонстрирует интеграцию Compose Multiplatform UI компонентов с React Native библиотекой.

## Структура проекта

- `library/` - Compose Multiplatform библиотека с UI компонентами
- `rn-lib/` - React Native библиотека с нативными компонентами
- `example-rn/` - Пример приложения React Native

## Что было сделано

### 1. Compose Multiplatform библиотека (`library/`)

- Создана Composable функция `RnLibView()` в `library/src/commonMain/kotlin/io/github/kotlin/compose/RnLibView.kt`
- Настроены зависимости для Android и iOS
- Исправлены проблемы с совместимостью версий Kotlin

### 2. React Native Android интеграция (`rn-lib/android/`)

- Обновлен `RnLibView.kt` для использования `ComposeView`
- Добавлены зависимости на Compose UI
- Интегрирована библиотека `library`
- Обновлены настройки Kotlin до версии 2.2.0

### 3. Настройки проекта

- Создан `rn-lib/settings.gradle` для включения `library` проекта
- Обновлены версии и настройки компиляции
- Увеличена память для Gradle сборки

## Как использовать

### Android

1. Соберите проект:
```bash
./gradlew :library:build
```

2. В React Native приложении используйте:
```javascript
import { RnLibView } from 'rn-lib';

// В компоненте
<RnLibView color="#32a852" style={styles.box} />
```

### iOS (TODO)

iOS интеграция пока не завершена из-за сложностей с зависимостями Compose Multiplatform.

## Основные изменения

### Android RnLibView.kt
```kotlin
class RnLibView : ComposeView {
  private fun setupCompose() {
    setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
    setContent {
      ComposableRnLibView()
    }
  }
}
```

### Build.gradle изменения
- Добавлена зависимость `implementation project(':library')`
- Обновлены версии Compose и Kotlin
- Добавлен Compose compiler plugin

## Следующие шаги

1. Завершить iOS интеграцию
2. Создать более сложные UI компоненты
3. Добавить передачу параметров из React Native в Compose
4. Оптимизировать производительность
