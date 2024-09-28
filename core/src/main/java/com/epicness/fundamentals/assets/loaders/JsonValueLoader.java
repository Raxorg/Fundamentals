package com.epicness.fundamentals.assets.loaders;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SynchronousAssetLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

@SuppressWarnings("rawtypes")
public class JsonValueLoader extends SynchronousAssetLoader<JsonValue, JsonValueLoader.JsonValueParameter> {

    public JsonValueLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public JsonValue load(AssetManager assetManager, String fileName, FileHandle file, JsonValueParameter parameter) {
        return new JsonReader().parse(file.readString());
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, JsonValueParameter parameter) {
        return null;
    }

    public static class JsonValueParameter extends AssetLoaderParameters<JsonValue> {
    }
}