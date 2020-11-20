
long result = Long.MAX_VALUE;
    // System.out.println(result);
    // ArrayList<Point> pts = new ArrayList<>(numRobots);
    // for (int i = 0; i < numRobots; i++) {
    //     pts.add(new Point(i, positionX.get(i), positionY.get(Y)));
    // }

    for (int i = 0; i < numRobots - 1; i++) {
    for (int j = i + 1; j < numRobots; j++) {

    long diffX = positionX.get(i) - positionX.get(j);
    long diffY = positionY.get(i) - positionY.get(j);
    // System.out.println("after calculation diffX: " + diffX);
    // System.out.println("after calculation diffY: " + diffY);
    long dist = (diffX * diffX) + (diffY * diffY);
    // System.out.println(dist);
    if (dist == 0) {
    continue;
    }
    result = dist < result ? dist : result;
    }

    }
    return result;
