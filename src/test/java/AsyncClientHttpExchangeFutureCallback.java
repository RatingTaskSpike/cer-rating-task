/**
 * Created by DennyCloud on 14-1-22.
 */

/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
package org.apache.http.examples.nio.client;

import java.util.concurrent.CountDownLatch;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

/**
 * This example demonstrates a fully asynchronous execution of multiple HTTP exchanges
 * where the result of an individual operation is reported using a callback interface.
 */
public class AsyncClientHttpExchangeFutureCallback {

    public static void main(final String[] args) throws Exception {
//        RequestConfig requestConfig = RequestConfig.custom()
//                .setSocketTimeout(3000)
//                .setConnectTimeout(3000).build();
//        CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom()
//                .setDefaultRequestConfig(requestConfig)
//                .build();
//        try {
//            httpclient.start();
//            HttpPost httpPost = new HttpPost("http://www.apache.org/");
//            httpPost.get
//            final CountDownLatch latch = new CountDownLatch(requests.length);
//                httpclient.execute(request, new FutureCallback<HttpResponse>() {
//
//                    public void completed(final HttpResponse response) {
//                        latch.countDown();
//                        System.out.println(request.getRequestLine() + "->" + response.getStatusLine());
//                    }
//
//                    public void failed(final Exception ex) {
//                        latch.countDown();
//                        System.out.println(request.getRequestLine() + "->" + ex);
//                    }
//
//                    public void cancelled() {
//                        latch.countDown();
//                        System.out.println(request.getRequestLine() + " cancelled");
//                    }
//
//                });
//            latch.await();
//            System.out.println("Shutting down");
//        } finally {
//            httpclient.close();
//        }
//        System.out.println("Done");
    }

}