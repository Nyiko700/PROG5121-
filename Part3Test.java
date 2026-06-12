/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.part3
/**
 *
 * @author nyiko
 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Part3Test {

    // Test adding a sent message
    @Test
    public void testAddSentMessage() {

        Part3.sentMessages.clear();
        Part3.storedMessages.clear();
        Part3.disregardedMessages.clear();
        Part3.messageHashes.clear();
        Part3.messageIDs.clear();
        Part3.recipients.clear();

        Part3.addMessage(
                "+27834557896",
                "Did you get the cake?",
                "Sent"
        );

        assertEquals(1, Part3.sentMessages.size());
    }

    // Test adding a stored message
    @Test
    public void testAddStoredMessage() {

        Part3.sentMessages.clear();
        Part3.storedMessages.clear();
        Part3.disregardedMessages.clear();

        Part3.addMessage(
                "+27838884567",
                "Where are you?",
                "Stored"
        );

        assertEquals(1, Part3.storedMessages.size());
    }

    // Test adding a disregarded message
    @Test
    public void testAddDisregardedMessage() {

        Part3.sentMessages.clear();
        Part3.storedMessages.clear();
        Part3.disregardedMessages.clear();

        Part3.addMessage(
                "+27834484567",
                "Hello",
                "Disregard"
        );

        assertEquals(1, Part3.disregardedMessages.size());
    }

    // Test message hash creation
    @Test
    public void testMessageHashCreated() {

        Part3.sentMessages.clear();
        Part3.messageHashes.clear();
        Part3.messageIDs.clear();
        Part3.recipients.clear();

        Part3.addMessage(
                "+27834557896",
                "Did you get the cake?",
                "Sent"
        );

        assertFalse(Part3.messageHashes.isEmpty());
    }

    // Test message ID creation
    @Test
    public void testMessageIDCreated() {

        Part3.sentMessages.clear();
        Part3.messageHashes.clear();
        Part3.messageIDs.clear();
        Part3.recipients.clear();

        Part3.addMessage(
                "+27834557896",
                "Test message",
                "Sent"
        );

        assertFalse(Part3.messageIDs.isEmpty());
    }

    // Test recipient saved
    @Test
    public void testRecipientSaved() {

        Part3.sentMessages.clear();
        Part3.recipients.clear();

        Part3.addMessage(
                "+27834557896",
                "Hello",
                "Sent"
        );

        assertEquals(
                "+27834557896",
                Part3.recipients.get(0)
        );
    }

    // Test sent messages list
    @Test
    public void testDisplaySentMessages() {

        Part3.sentMessages.clear();

        Part3.addMessage(
                "+27834557896",
                "Testing",
                "Sent"
        );

        assertTrue(
                Part3.sentMessages.contains("Testing")
        );
    }

    // Test delete message by hash
    @Test
    public void testDeleteMessageByHash() {

        Part3.sentMessages.clear();
        Part3.storedMessages.clear();
        Part3.messageHashes.clear();
        Part3.messageIDs.clear();
        Part3.recipients.clear();

        Part3.addMessage(
                "+27838884567",
                "Stored Message",
                "Stored"
        );

        String hash = Part3.messageHashes.get(0);

        Part3.deleteMessageByHash(hash);

        assertEquals(0, Part3.storedMessages.size());
    }
}
```
