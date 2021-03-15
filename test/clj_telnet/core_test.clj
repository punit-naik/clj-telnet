(ns clj-telnet.core-test
  (:require [clojure.test :refer [deftest testing is]]
            [clj-telnet.core :as core]))

(deftest init-test
  (testing "Init fn"
    (let [x (atom false)
          on-connect (fn [_] (reset! x true))
          s (core/init {:on-connect on-connect})]
      (is (true? @x))
      (core/destroy s))))

(deftest destroy-test
  (testing "Destroy fn"
    (let [x (atom false)
          on-close (fn [_ _] (reset! x true))
          s (core/init {:on-close on-close})]
      (core/destroy s)
      (is (true? @x)))))

(deftest send-message-test
  (testing "Send message fn"
    (let [message "test message"
          x (atom nil)
          on-receive (fn [m] (reset! x m))
          s (core/init {:on-receive on-receive})]
      (core/send-message s message)
      (Thread/sleep 1000)
      (is (= @x message))
      (core/destroy s))))

(deftest user-prompt-test
  (testing "User prompt fn"
    (let [message "test message"
          x (atom nil)
          on-receive (fn [m] (reset! x m))
          s (core/init {:on-receive on-receive})]
      (with-in-str message
        (core/user-prompt s))
      (Thread/sleep 1000)
      (is (= @x message))
      (core/destroy s))))
