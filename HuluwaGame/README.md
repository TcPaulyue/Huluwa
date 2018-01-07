这是一个葫芦娃大战妖精的程序，基于应用示例，并在该架构中进行自定义和完善。
主要介绍一下类结构，线程调度，战斗过程，复盘操作以及测试

1.类结构
Player类继承thing2D类，implements Runnable接口；

有私有成员变量：
Field:代表每个Player在的Field/name:姓名/
public：
level：Player的战斗力/personality:Player的阵营/STATE：Player存亡状态/Enemys:敌人/friends：朋友
主要的成员函数：
void printInf(...)//每一次执行信息写入文件
void setEnenys(...)//确定敌人
void setFriends(...)//确定朋友
void move(...)//移动
void killEnemy()/killMyself()/dieTog()//战斗过程的更新
void run()//每个线程的执行过程

Goodman类继承Player类；
Huluwa,yeye继承Goodman类；

Badman类继承Player类；
luoluo,xiezijing,shejing继承Badman类；

Ground类是显示背景类；

Field类中有
void initWorld(...)//将整个布局初始化
void buildWorld(...)//构造整个布局
void paint(...)//为消除页面闪屏重写的paint
void keyPressed(...)//按键处理，包括文件的读写，线程的创建
.......

2.线程调度
在keyPressed（）函数中用线程池给每一个player创建一个线程。
在Player类的run（）函数中判断this player的状态，如果this.st==STATE.DIE则终止该线程，否则继续执行；
每个线程执行时Sleep一段时间；
同样在run（）中判断如果该player的所有Enemy全部死亡则终止该线程；
这样就可以保证线程状态的正常。

3.战斗过程

主要在player的move（）和run中进行操作
战斗开始，player判断周围一定范围内是否有敌人出现且敌人是活着的，则进行攻击；
当战斗力大于敌人时，敌人牺牲，自身的战斗力一定程度衰减，继续前进；二者战斗力相同时，同归于尽；否则自己牺牲，敌方战斗力衰减。

move过程中向前运动距离固定，上下运动距离random，当判断到下一步会撞上队友，则此次移动取消；
当运动到边界时，反方向运动，且攻击范围变大，战斗力不变，直到战斗结束。

战斗结束显示战斗结果

4.复盘操作
战斗记录：
Field类的keyPressed（）函数中定义一个file和writer用于记录线程开始后的战斗过程；
对于每个Player，记录其name,x,y,state和阵营，当state为DIE时，随着该线程的关闭不进行记录；
当this.Enemy都死亡时，关闭file,即战斗结束
战斗复盘：
即在对话框中选择需要复盘的某次战斗，当key==KeyEvent.VK_L时
用JFileChooser创建对话框，定义一个file和reader，提取战斗记录文件中的name，x,y和STATE；
如果STATE==LIVE则移动到指定坐标，否则this.st=STATE.DIE

5.测试
主要测试了player中的几个方法。
