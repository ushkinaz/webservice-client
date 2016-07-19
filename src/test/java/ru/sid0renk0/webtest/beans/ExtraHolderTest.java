package ru.sid0renk0.webtest.beans;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ExtraHolderTest {

    private ExtraHolder subj;

    @Test
    public void changesPropagated() throws Exception {
        //given
        subj = new SimpleExtraHolder();

        //when
        subj.getExtra()
            .add(new Extra("name", "anton"));

        //then
        assertThat(
            subj.getExtraValue("name"),
            equalTo("anton"));
    }

    @Test
    public void subsequentChangesPropagated() throws Exception {
        //given
        subj = new SimpleExtraHolder();

        //when
        subj.getExtra()
            .add(new Extra("name", "anton"));
        subj.getExtra()
            .add(new Extra("password", "pass"));

        //then
        assertThat(
            subj.getExtraValue("password"),
            equalTo("pass"));
        assertThat(
            subj.getExtra(),
            hasSize(2));
    }


    @Test
    public void duplicatingProperties() throws Exception {
        //given
        subj = new SimpleExtraHolder();

        //when
        subj.setExtraValue("name", "first");
        subj.setExtraValue("name", "second");

        //then
        assertThat(
            subj.getExtraValue("name"),
            equalTo("second"));

        assertThat(
            subj.getExtra(),
            hasSize(1));
    }

    @Test
    public void listInitialized() throws Exception {
        //given
        subj = new SimpleExtraHolder();

        //when

        //then
        assertThat(
            subj.getExtra(),
            notNullValue());
    }

    private static class SimpleExtraHolder extends ExtraHolder {
    }
}
