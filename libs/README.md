# Libs Folder

Place Terrablender jar file here for local dependency resolution.

## Instructions:

1. Download Terrablender for Minecraft 1.20.1 from:
   https://www.curseforge.com/minecraft/mc-mods/terrablender/files

2. Place the downloaded jar file in this folder with a name like:
   `TerraBlender-forge-1.20.1-X.X.X.jar`

3. Update the dependency in `build.gradle` to reference the file name (without .jar extension)

Example:
```gradle
implementation fg.deobf("blank:TerraBlender-forge-1.20.1-X.X.X")
```

4. Run `./gradlew --refresh-dependencies` to resolve the local dependency

5. Uncomment Terrablender code in:
   - `src/main/java/net/kpr/makers_crysknife_mod/worldgen/ModBiomeRegions.java`
   - `src/main/java/net/kpr/makers_crysknife_mod/MakersCrysknifeMod.java`

