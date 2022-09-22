/**
 * @format
 */

import {AppRegistry} from 'react-native';
import App from './App';
import {name as appName} from './app.json';
import headlessTask from './headless'
import notifee, { EventType } from '@notifee/react-native';



notifee.onBackgroundEvent(async ({ type, detail }) => {
    const { notification, pressAction } = detail;
  
    // Check if the user pressed the "Mark as read" action
    if (type === EventType.TRIGGER_NOTIFICATION_CREATED) {
      // Update external API
      // Remove the notification
        //don't run while testing      
      //await notifee.cancelNotification(notification.id);
    }
  });

//gitemoji test  
AppRegistry.registerHeadlessTask('HeadlessAction',()=>headlessTask)
AppRegistry.registerComponent(appName, () => App);
