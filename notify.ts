import notifee, { TimestampTrigger, TriggerType } from '@notifee/react-native';
import { PermissionsAndroid, ToastAndroid } from 'react-native';


const handleNotify = async () => {
    try {
        console.log('notification running');

        ToastAndroid.show('Notify', ToastAndroid.SHORT)
        // Request permissions (required for iOS)
        await notifee.requestPermission()

        // Create a channel (required for Android)
        const channelId = await notifee.createChannel({
            id: 'default',
            name: 'Default Channel',
        });

        const date = new Date(Date.now());
        //date.setMinutes(date.getMinutes()+1)
        date.setSeconds(date.getSeconds() + 10)

        // Create a time-based trigger
        const trigger: TimestampTrigger = {
            type: TriggerType.TIMESTAMP,
            timestamp: date.getTime(), // fire at 11:10am (10 minutes before meeting)
            alarmManager: {
                allowWhileIdle: true,
            },
        };

        // Create a trigger notification
        await notifee.createTriggerNotification(
            {
                title: 'Meeting with Jane',
                body: 'Today at 11:20am',
                android: {
                    channelId,
                    smallIcon: 'ic_launcher', // optional, defaults to 'ic_launcher'.
                    // pressAction is needed if you want the notification to open the app when pressed
                },
            },
            trigger,
        );
    } catch (error) {
        let message = (error as Error).message
        ToastAndroid.show('Error' + message, ToastAndroid.SHORT)
    }
}

export default handleNotify