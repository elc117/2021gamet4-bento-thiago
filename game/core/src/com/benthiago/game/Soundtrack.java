package com.benthiago.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Disposable;

import java.util.Random;

public class Soundtrack implements Disposable {

    enum State {NOT_PLAYING, PLAYING};
    State state;
    String[] musicas = new String[]{
            "1043738_Full-Force.mp3"
            , "1049123_Meet-Your-Maker.mp3"
            , "1054853_Paradisum.mp3"
            , "1068244_Arcane-Behemoth.mp3"};
    int currentMusic;
    Music playing;

    Soundtrack() {
        state = State.NOT_PLAYING;
        currentMusic = new Random().nextInt(musicas.length);
        nextMusic();
    }

    public void nextMusic() {
        if (playing != null) {
            playing.stop();
            playing.dispose();
        }
        currentMusic = (currentMusic + 1) % musicas.length;
        playing = Gdx.audio.newMusic(Gdx.files.internal(musicas[currentMusic]));
        playing.setVolume(0.1f);
    }

    public void play() {
        if (playing == null) {
            nextMusic();
        }
        state = State.PLAYING;
        playing.play();
    }

    public void pause() {
        if (playing == null) {
            nextMusic();
        }
        state = State.NOT_PLAYING;
        playing.pause();
    }

    public void toggle() {
        if (state == State.NOT_PLAYING) {
            play();
        }
        else
        {
            pause();
        }
    }

    public void keepPlaying() {
        if (state == State.PLAYING && playing != null && !playing.isPlaying()) {
            nextMusic();
            play();
        }
    }

    @Override
    public void dispose() {
        if (playing != null) {
            playing.dispose();
        }
    }
}
