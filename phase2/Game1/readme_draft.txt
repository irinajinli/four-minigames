
Work done by Luqiong Xie for phase 2 but git pushed to phase 1 folder:

1. I migrated tapping game to the existing Activity-View-GameManager structure so that 
1) Tapping game can use the super class as apple game, such as GameItem, GameManager, GameView. 
2) Tapping game can integrate with the existing program that provides statistics extracting and customization logic. 

2. I fixed tapping game bug so that the main menu screen won't get reload if user click return button when the game is running.

Classes created: SpeedDisplayer, StarDisplayer, 
Classes updated: all tapping game .java files are updated, including TappingActivity,  TappingGameView, TappingCircle, GameItem, TappingGameManager
*****************************************************Here is the git log from Nov 2 2019 to Nov 14 2019. 
*****************************************************commit dede32d5405105b1cb1b92e24e05f21edfff7d60
Author: Luqiong Xie <luqiong.xie@mail.utoronto.ca>
Date:   Thu Nov 14 10:41:04 2019 -0500

    Moving Game1 folder and all its files from phase1 to phase2

commit 1dae6971fca2c4fc62d4fa3fb5895a0d236e5903
Author: Luqiong Xie <luqiong.xie@mail.utoronto.ca>
Date:   Wed Nov 13 23:54:14 2019 -0500

    Cancelled CountDownTimer when thread stopped running

commit 9e22ca61320241b26b779a3ae98b6d3ed1937012
Merge: b7b9785 39838b4
Author: ashley <ash.lu@mail.utoronto.ca>
Date:   Tue Nov 12 22:38:37 2019 -0500

    Merge branch 'master' of https://markus.teach.cs.toronto.edu/git/csc207-2019-09/group_0635

commit 39838b42c5cf6382d45cbadcf03a191901d16130
Author: Luqiong Xie <luqiong.xie@mail.utoronto.ca>
Date:   Sat Nov 9 20:19:45 2019 -0500

    deleting unnecessary pug.png

commit c53be06589ea5415a6ff9e6a0a2588c871f5e003
Author: Luqiong Xie <luqiong.xie@mail.utoronto.ca>
Date:   Sat Nov 9 20:15:10 2019 -0500

    Added customization for tapping game by modifying TappingGameManager.java, TappingGameView.java. Moved Background.java to common as it is also used by tapping game. May have added space in AppleGameManager. Added .png files for character customization.

commit de5ee7acadbfbe42f6849be2180347f22aa9ab4c
Author: Luqiong Xie <luqiong.xie@mail.utoronto.ca>
Date:   Sat Nov 9 17:28:43 2019 -0500

    Deleted unnecessary code in Tapping GameManager.java

commit 1607d63a4a6d4846c3254a81063ffb601b15ca13
Author: Luqiong Xie <luqiong.xie@mail.utoronto.ca>
Date:   Sat Nov 9 17:27:05 2019 -0500

    Deleted TappingItem as Tapping Game items uses GameItem as super class

commit ccfc72c996c385eea5900706f067f8c56d876474
Author: Luqiong Xie <luqiong.xie@mail.utoronto.ca>
Date:   Sat Nov 9 17:24:30 2019 -0500

    Changed GameItem.drawString to avoid duplicated code in child class. Child classes of tappling game and apple game changed accordingly. Completed TappingGameManager and TappingGameView integration. Deleted duplicated MainThread for tapping game so that tapping game and apple game use same MainThread.java. Statistics works and tapping game exits normally

commit 6bd3c933aeab6087206737bc1adec706f35079e2
Author: Luqiong Xie <luqiong.xie@mail.utoronto.ca>
Date:   Sat Nov 9 13:18:34 2019 -0500

    Modifying GameItem.java so that Tapping Game item can use the same game item. Fixing Tapping Game bugs and adding OnClickListener

commit b2137a26f2a3cdda192919f10a883a804f1428ca
Author: Luqiong Xie <luqiong.xie@mail.utoronto.ca>
Date:   Sat Nov 2 03:09:14 2019 -0400

    Updating TappingActivity and TappingGameView

commit e5405a433753e0669905f62578f624c6ea580e04
Author: Luqiong Xie <luqiong.xie@mail.utoronto.ca>
Date:   Sat Nov 2 03:03:29 2019 -0400

    Adding / Updating Tapping game files

commit 4e675d95a0771b2c3b3eb1aa6af32364c6b1bde9
Author: Luqiong Xie <luqiong.xie@mail.utoronto.ca>
Date:   Sat Nov 2 02:57:52 2019 -0400

    Adding tapping game SpeedDisplayer.java and StarDisplayer.java

commit 3c47887db5853937d4867194dc098fa5cc730c0a
Author: Luqiong Xie <luqiong.xie@mail.utoronto.ca>
Date:   Sat Nov 2 02:56:43 2019 -0400

    Adding tapping game SpeedDisplayer.java and StarDisplayer.java

commit 5c4ae553b0874cef1b1908baede1fe071d1a7f36
Author: Luqiong Xie <luqiong.xie@mail.utoronto.ca>
Date:   Sat Nov 2 02:54:19 2019 -0400

    Changing TappingCircle.java

commit 1f647f3d6353a7f59357b26071212b341341de9f
Author: Luqiong Xie <luqiong.xie@mail.utoronto.ca>
Date:   Sat Nov 2 02:51:41 2019 -0400

    Changing Tapping Game classes