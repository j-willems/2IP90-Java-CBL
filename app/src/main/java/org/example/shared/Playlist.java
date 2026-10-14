package org.example.shared;

import org.example.shared.Track;

public class Playlist {

    Node current;
    Node head;
    int size;

    public Playlist() {
        this.current = null;
        this.head = null;
        this.size = 0;
    }

    public int getSize() {
        return this.size;
    }

    public Track getTrackAtIndex(int index) {
        if (this.head == null) {
            return null;
        }

        Node temp = this.head;

        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }

        return temp.track;
    }

    public void clear() {
        this.current = null;
        this.head = null;
        this.size = 0;
    }

    public void appendTrack(Track track) {
        this.size++;

        if (this.head == null) {
            this.head = new Node(track);

            return;
        }

        Node temp = new Node(track, this.head.prev, this.head);

        this.head.prev.next = temp;
        this.head.prev = temp;
    }

    public void prependTrack(Track track) {
        this.appendTrack(track);
        this.head = this.head.prev;
    }

    public boolean insertTrack(Track track, int position) {
        if ((position < 0) | (position > this.size)) {
            return false;
        }

        if (this.head == null) {
            this.head = new Node(track);
        }

        Node temp = this.head;
        for (int i = 0; i < position; i++) {
            temp = temp.next;
        }

        Node newNode = new Node(track, temp.prev, temp);
        temp.prev.next = newNode;
        temp.prev = newNode;

        this.size++;
        return true;
    }

    public boolean deleteTrack(int position) {
        if ((position < 0) | (position > this.size) | (this.head == null)) {
            return false;
        }

        Node temp = this.head;
        for (int i = 0; i < position; i++) {
            temp = temp.next;
        }

        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;

        this.size--;
        return true;
    }

    public Track previousTrack() {
        if (this.head == null) {
            return null;
        }

        this.current = this.current.prev;

        return this.current.track;
    }

    public Track nextTrack() {
        if (this.head == null) {
            return null;
        }

        this.current = this.current.next;

        return this.current.track;
    }

    public void traverse() {
        Node temp = this.head;
        for (int i = 0; i <= this.size; i++) {
            System.out.println(
                temp.track +
                    ", prev: " +
                    temp.prev.track +
                    ", next: " +
                    temp.next.track
            );

            temp = temp.next;
        }
    }

    class Node {

        Track track;
        Node prev;
        Node next;

        Node(Track track) {
            this.track = track;
            this.prev = this;
            this.next = this;
        }

        Node(Track track, Node prev, Node next) {
            this.track = track;
            this.prev = prev;
            this.next = next;
        }
    }
}
