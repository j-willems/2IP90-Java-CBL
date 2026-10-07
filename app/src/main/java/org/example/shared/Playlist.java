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

    public Track firstTrack() {
        if (this.head == null) {
            return null;
        }

        return this.head.track;
    }

    public Track nextTrack() {
        this.current = this.current.next;

        return this.current.track;
    }

    public Track previousTrack() {
        this.current = this.current.prev;

        return this.current.track;
    }

    public void appendTrack(Track track) {
        if (this.head == null) {
            this.head = new Node(track);

            return;
        }

        Node temp = new Node(track, this.head.prev, this.head);

        this.head.prev.next = temp;
        this.head.prev = temp;

        this.size++;
    }

    public void prependTrack(Track track) {
        this.appendTrack(track);
        this.head = this.head.prev;
    }

    public boolean insertTrack(Track track, int position) {
        if ((position < 0) | (position > this.size)) {
            return false;
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
        if ((position < 0) | (position > this.size)) {
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
