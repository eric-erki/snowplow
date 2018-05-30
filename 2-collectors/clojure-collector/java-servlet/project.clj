;;;; Copyright (c) 2012-2013 Snowplow Analytics Ltd. All rights reserved.
;;;;
;;;; This program is licensed to you under the Apache License Version 2.0,
;;;; and you may not use this file except in compliance with the Apache License Version 2.0.
;;;; You may obtain a copy of the Apache License Version 2.0 at http://www.apache.org/licenses/LICENSE-2.0.
;;;;
;;;; Unless required by applicable law or agreed to in writing,
;;;; software distributed under the Apache License Version 2.0 is distributed on an
;;;; "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
;;;; See the Apache License Version 2.0 for the specific language governing permissions and limitations there under.

;;;; Author:    Alex Dean (mailto:support@snowplowanalytics.com)
;;;; Copyright: Copyright (c) 2012-2013 Snowplow Analytics Ltd
;;;; License:   Apache License Version 2.0

(require 'cemerick.pomegranate.aether)
(cemerick.pomegranate.aether/register-wagon-factory!
  "http" #(org.apache.maven.wagon.providers.http.HttpWagon.))

(defproject snowplow/clojure-collector "2.0.0" ;; MUST also bump version in server.xml
  :license {:name "Apache Version 2.0"
  :url "http://www.apache.org/licenses/LICENSE-2.0"}
  :description "A SnowPlow event collector written in Clojure. AWS Elastic Beanstalk compatible."
  :dependencies     [[org.clojure/clojure "1.9.0"]
                     [ring/ring-core "1.6.3"]
                     [ring/ring-devel "1.6.3"]
                     [compojure "1.6.1"]
                     [metrics-clojure "2.10.0"]
                     [metrics-clojure-ring "2.10.0"]
                     [commons-codec/commons-codec "1.7"]]
  ;; The jetty adapter is only used during development
  :profiles         {:dev {:dependencies [[ring/ring-jetty-adapter "1.6.3"]]}}
  :war-resources-path   "war-resources"
  :plugins          [[lein-ring "0.8.3"]
                     [lein-beanstalk "0.2.6"]]
  :ring {:handler snowplow.clojure-collector.beanstalk/app}) ; .beanstalk -> .core if you don't need Beanstalk support

