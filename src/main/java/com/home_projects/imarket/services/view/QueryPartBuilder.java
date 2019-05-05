package com.home_projects.imarket.services.view;

interface QueryPartBuilder {

    <T extends QueryPart> T build(PartType type);

}
