package com.benthiago.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Disposable;

public class Soundtrack implements Disposable {
    String[] musicas = new String[]{
            "1043738_Full-Force.mp3"
            , "1049123_Meet-Your-Maker.mp3"
            , "1054853_Paradisum.mp3"
            , "1068244_Arcane-Behemoth.mp3"};
    int currentMusic;
    Music playing;

    Soundtrack() {
        currentMusic = 0;
        playing = Gdx.audio.newMusic(Gdx.files.internal(musicas[currentMusic]));
        playing.setVolume(0.1f);
        playing.play();
    }

    public void playIfStopped(){
        if (!playing.isPlaying()) {
            playing.dispose();
            currentMusic = (currentMusic + 1) % musicas.length;
            playing = Gdx.audio.newMusic(Gdx.files.internal(musicas[currentMusic]));
            playing.setVolume(0.1f);
            playing.play();
        }
    }
    @Override
    public void dispose() {

    }
}
