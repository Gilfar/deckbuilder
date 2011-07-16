/**
 * Copyright (c) 2005-2006 JavaGameNetworking
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'JavaGameNetworking' nor the names of its contributors 
 *   may be used to endorse or promote products derived from this software 
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Created: Jun 24, 2006
 */
package com.captiveimagination.jgn.test.unit;

import com.captiveimagination.jgn.event.*;

/**
 * @author Matthew D. Hicks
 */
public class TestRealtimeMessage extends AbstractMessageServerTestCase {
	private int server1MessageCount = 0;
	private int server2MessageCount = 0;
	
	@SuppressWarnings("all")
	public void testRealtimeMessage() throws Exception {
		server1.addMessageListener(new DynamicMessageAdapter() {
			public void messageReceived(MyRealtimeMessage message) {
				System.out.println("S1> Received Message: " + message.getId());
				server1MessageCount++;
			}
		});
		server2.addMessageListener(new DynamicMessageAdapter() {
			public void messageReceived(MyRealtimeMessage message) {
				System.out.println("S2> Received Message: " + message.getId());
				server2MessageCount++;
			}
		});
		MyRealtimeMessage message = new MyRealtimeMessage();
		System.out.println("Message: " + message.getRealtimeId());
		for (int i = 0; i < 100; i++) {
			client1to2.sendMessage(message);
			client2to1.sendMessage(message);
		}
		Thread.sleep(1000);
		assertEquals(server1MessageCount, 1);
		assertEquals(server2MessageCount, 1);
	}
}
