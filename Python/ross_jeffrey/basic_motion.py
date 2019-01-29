import rclpy
import math
import time
from rclpy.node import Node
from std_msgs.msg import Float32
from std_msgs.msg import ByteMultiArray
from veranda.SimTimer import SimTimer
from sensor_msgs.msg import LaserScan
from struct import *

def angle_to_end(x,y,xe,ye):
    tempx = xe - x
    tempy = ye - y
    hyp = math.sqrt((tempx * tempx) + (tempy * tempy))
    return math.acos(tempx/hyp)

def turn_right(left, right):
    msg = Float32()

    msg.data = -1.0
    right = -1.0
    pubright.publish(msg)

    msg.data = 1.0
    left = 1.0
    publeft.publish(msg)

    return left, right

def turn_left(left, right):
    msg = Float32()

    msg.data = 1.0
    right = 1.0
    pubright.publish(msg)

    msg.data = -1.0
    left = -1.0
    publeft.publish(msg)
    return left, right


def go_straight(left, right):
    msg = Float32()

    msg.data = 1.0
    left = 1.0
    right = 1.0
    pubright.publish(msg)
    publeft.publish(msg)
    return left, right

def go_straight_reverse(left, right):
    msg = Float32()

    msg.data = -1.0
    left = -1.0
    right = -1.0
    pubright.publish(msg)
    publeft.publish(msg)
    return left, right

def stop(left, right):
    msg = Float32()

    msg.data = 0.0
    left = 0.0
    right = 0.0
    pubright.publish(msg)
    publeft.publish(msg)
    return left, right

def negTheta(th):
    if th > 0.0:
        return -(2*math.pi) + th
    if th < 0.0:
        return (2*math.pi) + th
    if th == 0.0:
        return 0.0

def calc_values(x,y,th,t,r,l,left,right):
    x = x + (r*t/2.0)*(left+right)*math.cos(th)
    y = y + (r*t/2.0)*(left+right)*math.sin(th)
    th = th + (r*t/(2.0*l))*(right-left)
    if th < -6.28319 or th > 6.28319:
        th = 0.0
    return x,y,th

def run():
    global x,goalx,goaly,y,th,t,r,l,left,right,turning
    x,y,th = calc_values(x,y,th,t,r,l,left,right)
    #print('x: {0:9f}      y: {1:9f}      th: {2:9f}'.format(x, y, th))
    #print("onCourse: " + str(run.onCourse) + " || turning: " + str(turning))
    if run.onCourse != True:
        if turning == False:
            print("here")
            #print("theta: " + str(th) + "   neg: " + str(negTheta(th)) + "   desired angle: " + str(angle_to_end(x,y,goalx,goaly)))
            if((th > angle_to_end(x,y,goalx,goaly) - 0.005 and
               th < angle_to_end(x,y,goalx,goaly) + 0.005) or
              (th > negTheta(angle_to_end(x,y,goalx,goaly)) - 0.005 and
               th < negTheta(angle_to_end(x,y,goalx,goaly)) + 0.005)):

                run.onCourse = True
            else:
                left,right = turn_right(left,right)
    if run.onCourse == True:
        if turning == False:
            left, right = go_straight(left,right)
        else:
            run.onCourse = False
            turning = False
run.onCourse = False

def checkScans(message):
    global x,y,th,r,l,left,right,turning
    #print("Turning here: " + str(turning))
    if turning != True:
        '''for i in range(0, len(message.ranges) - 1):
            print("Sensor " + str(i) + ": " + str(message.ranges[i]))'''
        if message.ranges[6] != float("inf"):
            turning = True
            left, right = go_straight_reverse(left,right)
            time.sleep(2)
            x,y,th = calc_values(x,y,th,2,r,l,left,right)
            print('x: {0:9f}      y: {1:9f}      th: {2:9f}'.format(x, y, th))

            left, right = turn_right(left,right)
            time.sleep(2)
            x,y,th = calc_values(x,y,th,2,r,l,left,right)
            print('x: {0:9f}      y: {1:9f}      th: {2:9f}'.format(x, y, th))

            left, right = go_straight(left,right)
            time.sleep(8)
            x,y,th = calc_values(x,y,th,8,r,l,left,right)
            print('x: {0:9f}      y: {1:9f}      th: {2:9f}'.format(x, y, th))

x = 0.0
y = 0.0
goalx = 0.0
goaly = 15.0
th = math.pi / 2.0
left = 0.0
right = 0.0
t = 0.01
r = 0.5
l = 0.6
turning = False
rclpy.init()
node = Node("Basic")

publeft = node.create_publisher(Float32, 'robot0/left_wheel')
pubright = node.create_publisher(Float32, 'robot0/right_wheel')
lidar = node.create_subscription(LaserScan, 'robot0/lidar', checkScans)

simTime = SimTimer(True, 'veranda/timestamp', node)
timer_handle = simTime.create_timer(0.01, run)

rclpy.spin(node)
node.destroy_node()
rclpy.shutdown()
