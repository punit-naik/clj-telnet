(ns clj-telnet.core
  (:require [gniazdo.core :as ws]))

(defn init
  "Sets up connection to a WS server using the URL provided
   Returns the socket connection object"
  [{:keys [on-receive on-connect on-close on-error url]
    :or {url "ws://echo.websocket.org"
         on-receive #(println (str "Received message from the server: \"" % "\""))
         on-connect #(println "Connected to server:" (-> % .getRequestURI .toString))
         on-close (fn [code reason] (println (str "Closed connection to the server with code " code " => " reason)))
         on-error #(println "Error occured" (ex-message %))}}]
  (ws/connect url
   :on-receive on-receive
   :on-close on-close
   :on-error on-error
   :on-connect on-connect))

(defn send-message
  [socket message]
  (ws/send-msg socket message))

(defn destroy
  [socket]
  (ws/close socket))

(comment
  (def s (init nil))
  (destroy s)
  s
  (send-message s "test message"))