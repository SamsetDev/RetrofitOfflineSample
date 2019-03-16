# RetrofitOfflineSample

<h1>Offline data ( Caching )</h1>

Caching is a way of temporarily storing data fetched from a network on a device’s storage, so that we can access it at a later time when the device is offline or if we want to access the same data again.

In this article, I’ll be talking about how to enable caching with Retrofit in your app, and store responses temporarily for later use. All this without using a database


<h2>Benefits of Caching</h2>
Reduces bandwidth consumption.<br>
Saves you time you’d spend waiting for the server to give you the network response.<br>
Saves the server the burden of additional traffic.<br>
If you need to access the same network resource again after having accessed it recently, your device won’t need to make a request to the server; it’ll get the cached response instead.<br>

<h1>Credits</h1>

Author: Sanjay Singh 

<h1>License</h1>

Copyright 2019 Sanjay Singh.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
