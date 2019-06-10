# LightAPI
A small and simple utility for modifying block light states.

### Obtaining a version-specific LightAPI instance.
```java
LightAPI.get();
```

### Setting a light level of a block.
```java
lightApi.setLight(World world, int x, int y, int z, int lightLevel);

lightApi.setLight(World world, Vector vector, int lightLevel);

lightApi.setLight(Location location, int lightLevel);
```

### Removing all light from a block.
```java
lightApi.removeLight(World world, int x, int y, int z);

lightApi.removeLight(World world, Vector vector);

lightApi.removeLight(Location location);
```

### Updating the light level of a block.
```java
lightApi.updateLight(World world, int x, int y, int z);

lightApi.updateLight(World world, Vector vector);

lightApi.updateLight(Location location);
```